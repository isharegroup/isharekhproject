package com.isharekh.domain.controllers.user;

import com.isharekh.CustomMessageType;
import com.isharekh.domain.models.user_security.SecUser;
import com.isharekh.domain.services.user.UserService;
import com.isharekh.routs.MainURL;
import com.isharekh.routs.MessageRout;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/*
Create By: Ron Rith
Create Date: 1/3/2018
*/
@RestController
@RequestMapping(value = MainURL.REST_USERS)
@Api(value="ishare", description="welcome to users api")
public class UserRestController {
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;

    private static String UPLOADED_FOLDER = "D:\\home\\isharekh_2\\isharekh_project\\src\\main\\resources\\static\\";

    private static String URL_IMAGE_MAIN = "http://localhost:8080/rest/users/img";

    public static final String URL_REST_USER = "https://isharekh.herokuapp.com/api/users";

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @ApiOperation(value = "TODO: VIEW LIST USERS",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = RequestMethod.GET)
    private List<SecUser> getAllUsers() {
        return  (List<SecUser>) userService.findAllUsers();
    }

    @ApiOperation(value = "TODO: SEARCH A USER BY ID", response = SecUser.class)
    @RequestMapping(value = MainURL.REST_ID, method = RequestMethod.GET)
    private ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        SecUser secUser = (SecUser) userService.getUserById(id);
        if (secUser != null) {
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_SEARCH_BY_ID_SUCCESS, MessageRout.MS_CODE_SUCCESS, secUser), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_SEARCH_BY_ID_FAULT, MessageRout.MS_CODE_FAULT, secUser), HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "TODO: SAVE USER",response = SecUser.class)
    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<?> saveUser(@RequestBody @Valid SecUser secUser) {
        if (secUser != null) {
            userService.saveUser(secUser);
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_SAVE_SUCCESS, MessageRout.MS_CODE_SUCCESS, secUser), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_SAVE_FAULT, MessageRout.MS_CODE_FAULT, secUser), HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "TODO: SHOW USER WITH PAGINATION",response = SecUser.class)
    @RequestMapping(value = MainURL.REST_SLASH,params = {"page","pageSize"},method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Page<SecUser> showPersonsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                         @RequestParam("page") Optional<Integer> page) {

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<SecUser> userProfiles = userService.findAllPageable(new PageRequest(evalPage, evalPageSize));
        return userProfiles;
    }

    @ApiOperation(value = "TODO: UPDATE USER",response = SecUser.class)
    @RequestMapping(value = MainURL.REST_ID, method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody @Valid SecUser secUser, @PathVariable("id") Long id) {
        if (secUser != null && id != null) {
            userService.updateUser(secUser, id);
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_UPDATE_SUCCESS, MessageRout.MS_CODE_SUCCESS, secUser), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_UPDATE_FAULT, MessageRout.MS_CODE_FAULT, secUser), HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "TODO: DELETE A USER BY ID", response = SecUser.class)
    @RequestMapping(value = MainURL.REST_ID, method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting SecUser with id " + id);

        SecUser secUser = userService.getUserById(id);
        if (secUser == null) {
            System.out.println("Unable to delete. secUser with id " + id + " not found");
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_DELETE_FAULT, MessageRout.MS_CODE_FAULT, secUser), HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(id);
        return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_DELETE_SUCCESS, MessageRout.MS_CODE_SUCCESS, secUser), HttpStatus.OK);
    }

    /*@ApiOperation(value = "TODO: GET IMAGE BY IMAGE NAME",response = News.class)
    @RequestMapping(value = MainURL.REST_IMG_NAME, method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable("imageName") String imageName)
            throws Exception {

        BufferedImage image = null;
        File f = null;
        HttpHeaders headers = new HttpHeaders();
        byte[] media = null;
        ResponseEntity<byte[]> responseEntity = null;

        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        try{
            f = new File(UPLOADED_FOLDER + imageName +".jpg"); //image file path
            image = ImageIO.read(f);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            media = baos.toByteArray();

            System.out.println("Reading complete.");
            responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        }catch(IOException e){
            System.out.println("Error: "+e);
            responseEntity = new ResponseEntity<>(media, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }*/

    /*@ApiOperation(value = "TODO: SAVE USER WITH FILE",response = News.class)
    @RequestMapping(value = MainURL.REST_USERS_IMG_CREATE, method = RequestMethod.POST)
    private void store(@RequestParam("file") MultipartFile file,
                       RedirectAttributes redirectAttributes, @Valid SecUser secUser) {
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
            secUser.setImageName(file.getOriginalFilename());
            secUser.setRealImageUrl(MainURL.URL_IMAGE_MAIN_SECURITY_USERS + file.getOriginalFilename());
            userService.saveUser(secUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*@ApiOperation(value = "TODO: UPDATE USER WITH FILE",response = News.class)
    @RequestMapping(value = MainURL.REST_USERS_IMG_UPDATE, method = RequestMethod.PUT)
    private void update(@RequestParam("file") MultipartFile file,
                        RedirectAttributes redirectAttributes, @Valid SecUser secUser, @PathVariable("id") Long id) {
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
            secUser.setImageName(file.getOriginalFilename());
            secUser.setRealImageUrl(MainURL.URL_IMAGE_MAIN_SECURITY_USERS + file.getOriginalFilename());
            userService.updateUser(secUser,id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private  HttpHeaders getHeaders(String email,String password){
        String plainCredentials = email + ":" + password;
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private SecUser getUserLogin(String email,String password){
        System.out.println("\nTesting listAllUsers API-----------");
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        HttpEntity<String> request = new HttpEntity<String>(getHeaders(email,password));

        try {
            //Incorrect Email or password
            ResponseEntity<List> response = restTemplate.exchange(URL_REST_USER, HttpMethod.GET, request, List.class);

            List<LinkedHashMap<String, Object>> usersMap = (List<LinkedHashMap<String, Object>>) response.getBody();

            try {
                SecUser secUser = userService.findUserByEmail(email);
                SecUser secUser1 = new SecUser();

                if (usersMap != null) {
                    for (LinkedHashMap<String, Object> map : usersMap) {
                        System.out.println("news : id=" + map.get("id"));
                        System.out.println("news : email=" + map.get("email"));

                        secUser1.setEmail(email);
                        secUser1.setId(secUser.getId());
                        secUser1.setActive(secUser.getActive());
                        secUser1.setGender(secUser.getGender());
                        secUser1.setImageName(secUser.getImageName());
                        secUser1.setRealImageUrl(secUser.getRealImageUrl());
                        secUser1.setName(secUser.getName());
                        secUser1.setLastName(secUser.getLastName());
                    }
                    return secUser1;
                } else {
                    System.out.println("No user exist----------");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "TODO: USER LOGIN",response = SecUser.class)
    @RequestMapping(value = "/login/{email}/{password}", method = RequestMethod.GET)
    private ResponseEntity<?> findSecurityUser(
            @PathVariable("email") String email,
            @PathVariable("password") String password) {
        SecUser secUser = getUserLogin(email,password);
        if (secUser != null) {
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_LOGIN_SUCCESS, 2222, secUser), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_LOGIN_FAULT, 9999, secUser), HttpStatus.NOT_FOUND);
    }

}
