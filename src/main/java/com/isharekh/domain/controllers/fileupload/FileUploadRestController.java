package com.isharekh.domain.controllers.fileupload;

import com.isharekh.CustomMessageType;
import com.isharekh.domain.models.fileupload.FileUpload;
import com.isharekh.domain.repositories.fileupload.FileUploadRepository;
import com.isharekh.routs.MainURL;
import com.isharekh.routs.MessageRout;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

/**
 * Created by : Ron Rith
 * Create Date: 01/28/2018.
 */
@RestController
@RequestMapping(value = "/api/uploadfile")
@Api(value="fileuploadapi", description="fileupload api for news app")
public class FileUploadRestController {
    @Autowired
    private FileUploadRepository fileUploadRepository;
    private static String UPLOADED_FOLDER = "D:\\home\\isharekh_2\\isharekh\\isharekh_project\\src\\main\\resources\\static\\images\\news\\";

    /*TODO: UPLOAD NEWS IMAGE*/
    @ApiOperation(value = "TODO: SAVE IMAGE",response = FileUpload.class)
    @RequestMapping(method = {RequestMethod.POST})
    @ResponseBody
    private ResponseEntity<?> store(@RequestParam("file") MultipartFile file,
                                    RedirectAttributes redirectAttributes,
                                    @Valid FileUpload fileUpload) {
        if (file.isEmpty() || file == null) {
            /*redirectAttributes.addFlashAttribute("message", "Please select a file to upload");*/
            fileUploadRepository.save(fileUpload);
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_SAVE_SUCCESS, MessageRout.MS_CODE_SUCCESS, fileUpload.getFileName()), HttpStatus.OK);
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

            String imageName = renameFile(file.getOriginalFilename());
            fileUpload.setFileName(MainURL.URL_IMAGE_MAIN_LOCAL + imageName);

            fileUploadRepository.save(fileUpload);
            System.out.println("===========");
            System.out.println(fileUpload.getFileName());
            System.out.println("===========");
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_SAVE_SUCCESS, MessageRout.MS_CODE_SUCCESS, fileUpload.getFileName()), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_SAVE_FAULT, MessageRout.MS_CODE_FAULT, fileUpload.getFileName()), HttpStatus.OK);
        }
    }

    /*TODO: GET NEWS IMAGE*/
    @ApiOperation(value = "TODO: IMAGE BY IMAGE NAME")
    @RequestMapping(value = "/img/{imageName}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable("imageName") String imageName)
            throws Exception {

        BufferedImage image = null;
        File f = null;
        HttpHeaders headers = new HttpHeaders();
        byte[] media = null;
        ResponseEntity<byte[]> responseEntity = null;

        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        try {
            f = new File(UPLOADED_FOLDER + imageName + ".jpg"); //image file path
            image = ImageIO.read(f);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            media = baos.toByteArray();

            System.out.println("Reading complete.");
            responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("Error: " + e);
            responseEntity = new ResponseEntity<>(media, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    /*TODO: UPLOAD FILE IMAGE*/
  /*
    @RequestMapping(value = "/noimg",method = {RequestMethod.POST})
    @ResponseBody
    private ResponseEntity<?> storeNoImage(@RequestBody @Valid FileUpload fileUpload) {
        if (fileUpload != null) {
            fileUploadRepository.save(fileUpload);
            return new ResponseEntity<Object>(new CustomMessageType("Data have been save no select image", 2222, fileUpload.getFileName()), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new CustomMessageType("Data have not save", 9999, fileUpload.getFileName()), HttpStatus.NO_CONTENT);
    } }*/
   /* *TODO Rename file*/
    public String renameFile(String oldNameFile) {
        Date today = new Date();
        //rename
        String newNameFile = "news-" + today.getTime() + "-" + UUID.randomUUID() + ".jpg";
        File oldFile = new File(UPLOADED_FOLDER + oldNameFile);
        File newFile = new File(UPLOADED_FOLDER + newNameFile);

        if (oldFile.renameTo(newFile)) {
            System.out.println("Rename succesful");
        } else {
            System.out.println("Rename failed");
        }
        return newNameFile;
    }
}
