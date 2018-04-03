package com.isharekh.domain.controllers.news;

import com.isharekh.CustomMessageType;
import com.isharekh.domain.models.news.NewsType;
import com.isharekh.domain.models.user_security.SecUser;
import com.isharekh.domain.repositories.news.NewsTypeRepository;
import com.isharekh.domain.services.newstype.NewsTypeService;
import com.isharekh.domain.services.user.UserService;
import com.isharekh.routs.MainURL;
import com.isharekh.routs.MessageRout;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by : Ron Rith
 * Create Date: 01/13/2018.
 */
@RestController
@RequestMapping(value = MainURL.REST_NEWS_TYPE)
@Api(value="newstypeiosapi", description="newstype api for news app")
public class NewsTypeRestController {
    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NewsTypeService newsTypeService;

    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;

    private static String UPLOADED_FOLDER = "D:\\web_develop\\heroku\\heroku2\\newiosapi\\src\\main\\resources\\static\\images\\news\\";

    @ApiOperation(value = "TODO: VIEW LIST NEWS TYPE",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity<?> getNewsType() {
        List<NewsType> newsTypes = (List<NewsType>) newsTypeRepository.findAll();
        if (newsTypes != null) {
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_GET_ALL_SUCCESS, MessageRout.MS_CODE_SUCCESS, newsTypes), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_GET_ALL_ERROR, MessageRout.MS_CODE_FAULT, newsTypes), HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "TODO: GET NEWS TYPE BY ID",response = NewsType.class)
    @RequestMapping(value = MainURL.REST_ID, method = RequestMethod.GET)
    private NewsType getById(@PathVariable("id") Long id) {
        return (NewsType) newsTypeRepository.findOne(id);
    }

    @ApiOperation(value = "TODO: SHOW NEWS TYPE WITH PAGINATION",response = NewsType.class)
    @RequestMapping(value = MainURL.REST_SLASH, params = {"page", "pageSize"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Page<NewsType> showPersonsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                          @RequestParam("page") Optional<Integer> page) {

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<NewsType> userProfiles = newsTypeService.findAllPageable(new PageRequest(evalPage, evalPageSize, Sort.Direction.DESC, "id"));
        return userProfiles;
    }

    @ApiOperation(value = "TODO: SAVE NEWS TYPE",response = NewsType.class)
    @RequestMapping(value = MainURL.REST_NEWS_TYPE_SAVE_MAIL, method = RequestMethod.POST)
    private void saveNewsType(@RequestBody @Valid NewsType newsType, @PathVariable("email") String email) {
        SecUser secUser = new SecUser();
        if (newsType != null) {
            secUser = userService.findUserByEmail(email + ".com");
            newsType.setSecUser(secUser);
            newsTypeRepository.save(newsType);
        }

    }

    @ApiOperation(value = "TODO: UPDATE NEWS TYPE BY ID",response = NewsType.class)
    @RequestMapping(value = MainURL.REST_NEWS_TYPE_UPDATE_MAIL, method = RequestMethod.PUT)
    private void updateNewsType(@RequestBody @Valid NewsType newsType, @PathVariable("id") Long id, @PathVariable("email") String email) {
        if (newsType != null) {
            SecUser secUser = userService.findUserByEmail(email + ".com");
            newsType.setSecUser(secUser);

            newsType.setId(id);
            newsTypeRepository.save(newsType);
        }
    }

    @ApiOperation(value = "TODO: DELETE NEWS TYPE BY ID",response = NewsType.class)
    @RequestMapping(value = MainURL.REST_ID, method = RequestMethod.DELETE)
    private ResponseEntity<NewsType> deleteNewsType(@PathVariable("id") Long id) {
        NewsType newsType = newsTypeRepository.findOne(id);
        /*create object secuse null for save update object user to null before delete*/
        SecUser secUser = null;

        if (newsType == null) {
            System.out.println("Id not found");
            return new ResponseEntity<NewsType>(HttpStatus.NOT_FOUND);
        }
        newsType.setSecUser(secUser);
        newsType.setId(id);
        newsTypeRepository.save(newsType);
        newsTypeRepository.delete(id);
        return new ResponseEntity<NewsType>(HttpStatus.NO_CONTENT);
    }

    /*store author with image*/
    /*@RequestMapping(value = MainURL.REST_NEWS_TYPE_IMG_CREATE, method = RequestMethod.POST)
    private void store(@RequestParam("file") MultipartFile file,
                       RedirectAttributes redirectAttributes,
                       @Valid NewsType newsType,
                       @PathVariable("email") String email) {
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

            SecUser secUser = userService.findUserByEmail(email + ".com");
            newsType.setIcon(file.getOriginalFilename());
            newsType.setSecUser(secUser);
            newsTypeRepository.save(newsType);
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
    /*@RequestMapping(value = MainURL.REST_NEWS_TYPE_IMG_UPDATE, method = RequestMethod.PUT)
    private void update(@RequestParam("file") MultipartFile file,
                        RedirectAttributes redirectAttributes,
                        @Valid NewsType newsType,
                        @PathVariable("id") Long id,
                        @PathVariable("email") String email) {
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

            SecUser secUser = userService.findUserByEmail(email + ".com");
            newsType.setIcon(file.getOriginalFilename());
            newsType.setSecUser(secUser);
            newsType.setId(id);

            newsTypeRepository.save(newsType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
