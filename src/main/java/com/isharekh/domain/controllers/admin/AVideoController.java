package com.isharekh.domain.controllers.admin;

import com.isharekh.domain.models.fileupload.FileBucket;
import com.isharekh.domain.models.videos.Video;
import com.isharekh.domain.services.videos.AVideoService;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

/*
Create By: Ron Rith
Create Date: 2/28/2018
*/
@Controller
@RequestMapping(value = "/videos")
public class AVideoController {
    @Autowired
    private AVideoService aVideoService;

    @Value("${ms.add.done:input data}")
    private String message = "input data";

    @Value("${ms.add.error}")
    private String messageError = "error";

    @Value("${ms.input.des.kh}")
    private String inputDesKh = "des kh";

    @Value("${ms.input.des.en}")
    private String inputDesEn = "des en";

    private static final String DEFAUL_INPUT_DES_KH = "<p>Input description khmer</p>\r\n";
    private static final String DEFAUL_INPUT_DES_EN = "Input description English";

    static String des = null;
    static  String desEn = null;
    static Integer numView = null;
    static Integer numShare = null;
    static String comment = null;
    static String remoteVideoURL = null;
    static int i = 0, j = 0, k = 0, l = 0, m = 0;


    private static String UPLOAD_LOCATION="D:\\home\\isharekh_2\\isharekh\\isharekh_project\\src\\main\\resources\\static\\videos\\";

    @RequestMapping(method = RequestMethod.GET)
    private ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView("/admin/videos/index");
        modelAndView.addObject("videos", aVideoService.getAllTShort());
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    private String createVideo(ModelMap model) {
        model.put("des_kh",this.inputDesKh);
        model.put("des_en",this.inputDesEn);
        FileBucket fileModel = new FileBucket();
        Video video = new Video();

        model.addAttribute("fileBucket",fileModel);
        model.addAttribute("video",video);

        return "/admin/videos/create";
    }

    @RequestMapping(value="/create", method = {RequestMethod.POST})
    public String singleFileUpload(@Valid Video video,@Valid FileBucket fileBucket ,ModelMap model/*BindingResult result,*/) throws IOException {
        Video video1 = new Video();

        if (video != null) {
            if (video.getDes() != null && !video.getDes().isEmpty()) {
                des = video.getDes();
                if (!DEFAUL_INPUT_DES_KH.equalsIgnoreCase(des)) {
                    i = 1;
                }else {
                    i = 2;
                }
            }else if (video.getDesEn() != null && !video.getDesEn().isEmpty()) {
                desEn = video.getDesEn();
                if (!DEFAUL_INPUT_DES_EN.equalsIgnoreCase(desEn)) {
                    j = 1;
                } else {
                    j =2;
                }
            } else if(video.getRemoteVideoUrl()!=null && !video.getRemoteVideoUrl().isEmpty()){
                remoteVideoURL = video.getRemoteVideoUrl();
                k = 1;
            }
            if(i == 1){
                model.put("message", this.message);
                model.put("des_kh",this.des);
            }else if(i == 2){
                model.put("message_error", this.messageError);
                model.put("des_kh",this.des);
            }
            if(j == 1){
                model.put("message_en", this.message);
                model.put("des_en",this.desEn);
            }else if(j == 2){
                model.put("message_en_error", this.messageError);
                model.put("des_en",this.desEn);
            }
            if (k == 1) {
                model.put("message_remmote", this.message);
            } else if (k == 0) {
                model.put("message_remote_error", this.messageError);
            }
            /*if (video.getNumView() != null) {
                numView = video.getNumView();
                video1.setNumView(numView);
                k = 1;
            }
            if (video.getNumShare() != null) {
                numShare = video.getNumShare();
                video1.setNumShare(numShare);
                l = 1;
            }
            if (video.getComment() != null) {
                comment = video.getComment();
                video1.setComment(comment);
                m = 1;
            }*/

        }
       /* try {*/
            if (i == 1 && j == 1 && k == 1) {
                video1.setDes(des);
                video1.setDesEn(desEn);
                video1.setRemoteVideoUrl(remoteVideoURL);
                aVideoService.save(video1);
                des = "";
                desEn = "";
                remoteVideoURL = "";
                i = 0;
                j = 0;
                k = 0;
                return "redirect:" + "/videos";
            }else {
                return "/admin/videos/create";
            }
       /* }catch (Exception e){
            e.printStackTrace();
            return "/admin/videos/index";
        }*/

        /*if (result.hasErrors()) {
            System.out.println("validation errors");
            return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();

            //Now do something with file...
            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));

            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            return "/admin/videos/success";
        }*/
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    private ModelAndView detailVideo(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/admin/videos/detail");
        if (id != null) {
            Video video = (Video) aVideoService.getById(id);
            modelAndView.addObject("video", video);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    private String deleteVideo(@PathVariable("id") Long id) {
        aVideoService.delete(id);
        return "redirect:" + "/videos";
    }

    @RequestMapping(value = "/edit/{id}")
    private ModelAndView editVideo(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/admin/videos/update");
        if (id != null) {
            Video video = (Video) aVideoService.getById(id);
            modelAndView.addObject("video", video);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    private String updateVideo(@Valid Video video,@PathVariable("id") Long id) throws Exception {
        Video videoFromDB =(Video) aVideoService.getById(id);
        if (id != null) {
            if (video.getDes() != null) {
                video.setDesEn(videoFromDB.getDesEn());
                video.setRemoteVideoUrl(videoFromDB.getRemoteVideoUrl());
                aVideoService.update(video, id);
            }
            if (video.getDesEn() != null) {
                video.setDes(videoFromDB.getDes());
                video.setRemoteVideoUrl(videoFromDB.getRemoteVideoUrl());
                aVideoService.update(video, id);
            }
            if (video.getRemoteVideoUrl() != null) {
                video.setDes(videoFromDB.getDes());
                video.setDesEn(videoFromDB.getDesEn());
                aVideoService.update(video, id);
            }
            return "redirect:" + "/videos";
        } else {
            return "redirect:" + "/videos/edit/" + id;
        }
    }
}
