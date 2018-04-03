package com.isharekh.domain.controllers.author;

import com.isharekh.CustomMessageType;
import com.isharekh.domain.models.authors.Author;
import com.isharekh.domain.models.user_security.SecUser;
import com.isharekh.domain.repositories.author.AuthorRepository;
import com.isharekh.domain.services.user.UserService;
import com.isharekh.routs.MainURL;
import com.isharekh.routs.MessageRout;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by : Ron Rith
 * Create Date: 01/12/2018.
 */
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping(value = MainURL.REST_AUTHOR)
@Api(value="authoriosapi", description="author api for news app")
public class AuthorRestController<T> {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserService userService;

    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;

    private static String UPLOADED_FOLDER = "D:\\web_develop\\heroku\\heroku2\\newiosapi\\src\\main\\resources\\static\\images\\authors\\";

    @ApiOperation(value = "TODO: VIEW AUTHOR",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity<?> getAllAuthors() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        if(authors.isEmpty()){
            return new ResponseEntity(new CustomMessageType(MessageRout.MS_GET_ALL_ERROR ,MessageRout.MS_CODE_FAULT,authors),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(new CustomMessageType(MessageRout.MS_GET_ALL_SUCCESS,MessageRout.MS_CODE_SUCCESS,authors),HttpStatus.OK);
    }

    @ApiOperation(value = "TODO: GET AUTHOR BY ID",response = Author.class)
    @RequestMapping(value = MainURL.REST_ID, method = RequestMethod.GET)
    private Author getById(@PathVariable("id") Long id) {
        if (id != null) {
            return (Author) authorRepository.findOne(id);
        } else {
            return null;
        }
    }

    @ApiOperation(value = "TODO: SAVE AUTHOR",response = Author.class)
    @RequestMapping(value = MainURL.REST_AUTHOR_SAVE, method = RequestMethod.POST)
    private ResponseEntity<?> saveAuthor(@RequestBody @Valid Author author, @PathVariable("userEmail") String userEmail) {
        SecUser secUser = userService.findUserByEmail(userEmail + ".com");
        if (author != null) {
            author.setSecUser(secUser);
            authorRepository.save(author);
            return new ResponseEntity<Object>(new CustomMessageType("Data have been save",2222,author),HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new CustomMessageType("Author no data",9999,author),HttpStatus.OK);
    }

    @ApiOperation(value = "TODO: UPDATE AUTHOR BY AUTHOR ID",response = Author.class)
    @RequestMapping(value = MainURL.REST_AUTHOR_UPDATE, method = RequestMethod.PUT)
    private void updateAuthor(@RequestBody @Valid Author author, @PathVariable("id") Long id, @PathVariable("userEmail") String userEmail) {
        SecUser secUser = userService.findUserByEmail(userEmail + ".com");
        if (author != null) {
            author.setSecUser(secUser);
            author.setId(id);
            authorRepository.save(author);
        }
    }

    @ApiOperation(value = "TODO: DELETE AUTHOR BY AUTHOR ID",response = Author.class)
    @RequestMapping(value = MainURL.REST_ID, method = RequestMethod.DELETE)
    public ResponseEntity<Author> deleteAuthor(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting SecUser with id " + id);

        Author author = authorRepository.findOne(id);
         /*create object secuse null for save update object user to null before delete*/
        SecUser secUser = null;
        if (author == null) {
            System.out.println("Unable to delete. secUser with id " + id + " not found");
            return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
        }
        author.setSecUser(secUser);
        author.setId(id);
        authorRepository.save(author);
        authorRepository.delete(id);
        return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
    }

    /*store author with image*/
    /*@RequestMapping(value = MainURL.REST_AUTHOR_IMG_CREATE, method = RequestMethod.POST)
    private void store(@RequestParam("file") MultipartFile file,
                       RedirectAttributes redirectAttributes,
                       @Valid Author author,
                       @PathVariable("userEmail") String userEmail) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

            SecUser secUser = userService.findUserByEmail(userEmail + ".com");
            author.setImageName(file.getOriginalFilename());
            author.setRealImageUrl(MainURL.URL_IMAGE_MAIN_AUTHOR + file.getOriginalFilename());
            author.setSecUser(secUser);
            authorRepository.save(author);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*get author image*/
    /*@RequestMapping(value = MainURL.REST_IMG_NAME, method = RequestMethod.GET)
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
    }*/

    /*update author with image/*/
    /*@RequestMapping(value = MainURL.REST_AUTHOR_IMG_UPDATE, method = RequestMethod.PUT)
    private void update(@RequestParam("file") MultipartFile file,
                        RedirectAttributes redirectAttributes,
                        @Valid Author author, @PathVariable("id") Long id,
                        @PathVariable("userEmail") String userEmail) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

            SecUser secUser = userService.findUserByEmail(userEmail + ".com");
            author.setImageName(file.getOriginalFilename());
            author.setRealImageUrl(MainURL.URL_IMAGE_MAIN_AUTHOR + file.getOriginalFilename());
            author.setSecUser(secUser);
            author.setId(id);

            authorRepository.save(author);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
