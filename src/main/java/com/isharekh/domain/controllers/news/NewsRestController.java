package com.isharekh.domain.controllers.news;

import com.isharekh.CustomMessageType;
import com.isharekh.domain.models.authors.Author;
import com.isharekh.domain.models.news.News;
import com.isharekh.domain.models.news.NewsType;
import com.isharekh.domain.models.user_security.SecUser;
import com.isharekh.domain.repositories.author.AuthorRepository;
import com.isharekh.domain.repositories.fileupload.FileUploadRepository;
import com.isharekh.domain.repositories.news.NewsRepository;
import com.isharekh.domain.repositories.news.NewsTypeRepository;
import com.isharekh.domain.services.news.NewsService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by : Ron Rith
 * Create Date: 01/14/2018.
 */
@RestController
@RequestMapping(value = "/api/news")
@Api(value="newsiosapi", description="news api for news app")
public class NewsRestController {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Autowired
    private NewsService newsService;

    @Autowired
    private FileUploadRepository fileUploadRepository;

    private static final String JPG = "jpg";
    private static final String PNG = "png";
    private static final String JPEG = "jpeg";

    private static final String JPGC = "JPG";
    private static final String PNGC = "PNG";
    private static final String JPEGC = "JPEG";

    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;

    private static String UPLOADED_FOLDER = "D:\\web_develop\\heroku\\heroku2\\newiosapi\\src\\main\\resources\\static\\images\\news\\";

    @ApiOperation(value = "TODO: VIEW LIST NEWS",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = RequestMethod.GET)
    private List<News> getNews() {
        return (List<News>) newsRepository.findAll();
    }

    @ApiOperation(value = "TODO: SHOW NEWS WITH PAGINATION",response = News.class)
    @RequestMapping(value = MainURL.REST_SLASH, params = {"page", "pageSize"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Page<News> showNewsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                      @RequestParam("page") Optional<Integer> page) {

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<News> userProfiles = newsService.findAllPageable(new PageRequest(evalPage, evalPageSize, Sort.Direction.DESC, "id"));
        return userProfiles;
    }

    @ApiOperation(value = "TODO: SEARCH NEWS BY ID",response = News.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private News getById(@PathVariable("id") Long id) {
        if (id != null) {
            return (News) newsRepository.findOne(id);
        } else {
            System.out.println("Id null");
            return null;
        }
    }
    /*correct mobile*/
    @ApiOperation(value = "TODO: SAVE NEWS",response = News.class)
    @RequestMapping(value = "/save/{newsTypeDesEn}/{email}/{authorEmail}",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
/*@RequestMapping(value = "/save/{newsTypeDesEn}/{email}/{authorEmail}",
        method = RequestMethod.POST)*/
    private ResponseEntity<?> saveNews(@RequestBody @Valid News news,
                                       @PathVariable("newsTypeDesEn") String newsTypeDesEn,
                                       @PathVariable("email") String email,
                                       @PathVariable("authorEmail") String authorEmail) {
        if (news != null) {
            NewsType newsType = newsTypeRepository.findByDesEn(newsTypeDesEn);
            SecUser secUser = userService.findUserByEmail(email);
            Author author = authorRepository.findByEmail(authorEmail + ".com");

            news.setNewsType(newsType);
            news.setSecUser(secUser);
            news.setAuthor(author);
            newsRepository.save(news);
            return new ResponseEntity<Object>(new CustomMessageType("Data has been save.",2222,news),HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new CustomMessageType("Data has not been save.",9999,news),HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "TODO: UPDATE NEWS BY ID",response = News.class)
    @RequestMapping(value = "/{id}/{newsTypeDesEn}/{email}/{authorEmail}", method = RequestMethod.PUT)
    private ResponseEntity<?> updateNews(@RequestBody @Valid News news,
                                         @PathVariable("id") Long id,
                                         @PathVariable("newsTypeDesEn") String newsTypeDesEn,
                                         @PathVariable("email") String email,
                                         @PathVariable("authorEmail") String authorEmail) {
        if (news != null && id != null) {
            NewsType newsType = newsTypeRepository.findByDesEn(newsTypeDesEn);
            SecUser secUser = userService.findUserByEmail(email);
            Author author = authorRepository.findByEmail(authorEmail + ".com");

            news.setNewsType(newsType);
            news.setSecUser(secUser);
            news.setAuthor(author);
            news.setId(id);
            newsRepository.save(news);
            return new ResponseEntity<Object>(new CustomMessageType("Data has been update.", 2222, news), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new CustomMessageType("Has not data for update.", 9999, news), HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "TODO: DELETE A NEWS BY ID",response = News.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private ResponseEntity<?> deleteNews(@PathVariable("id") Long id) {
        News news = newsRepository.findOne(id);
        /*create object secuse null for save update object user to null before delete*/
        NewsType newsType = null;
        SecUser secUser = null;
        Author author = null;

        if (news == null) {
            System.out.println("Id not found");
            return new ResponseEntity<Object>(new CustomMessageType("Data has not been delete. ID (" + id + ") not found", 9999, news), HttpStatus.NOT_FOUND);
        }

        news.setNewsType(newsType);
        news.setSecUser(secUser);
        news.setAuthor(author);
        news.setId(id);

        newsRepository.save(news);

        newsRepository.delete(id);
        return new ResponseEntity<Object>(new CustomMessageType("Data has been delete.", 2222, news), HttpStatus.OK);
    }
    /*store news with image*/
    /*@ApiOperation(value = "TODO: SAVE NEWS WITH FILE",response = News.class)
    @RequestMapping(value = "/img/create/{newsTypeDesEn}/{email}/{authorEmail}", method = RequestMethod.POST)
    private void store(@RequestParam("file") MultipartFile file,
                       RedirectAttributes redirectAttributes,
                       @Valid News news,
                       @PathVariable("newsTypeDesEn") String newsTypeDesEn,
                       @PathVariable("email") String email,
                       @PathVariable("authorEmail") String authorEmail) {
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

            NewsType newsType = newsTypeRepository.findByDesEn(newsTypeDesEn);
            SecUser secUser = userService.findUserByEmail(email);
            Author author = authorRepository.findByEmail(authorEmail + ".com");

            news.setImage(file.getOriginalFilename());
            news.setRealImageUrl(MainURL.URL_IMAGE_MAIN_NEWS + file.getOriginalFilename());
            news.setNewsType(newsType);
            news.setSecUser(secUser);
            news.setAuthor(author);

            newsRepository.save(news);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*get news image*/
    /*@ApiOperation(value = "TODO: GET IMAGE BY IMAGE NAME",response = News.class)
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
            String imgWithEx = "";
            String imgCutEx = "";
            String imgEx = "";

            Boolean bJpg = false;
            Boolean bPng = false;
            Boolean bJpeg = false;

            Boolean bJPG = false;
            Boolean bPNG = false;
            Boolean bJPEG = false;

            List<FileUpload> fileUploads = (List<FileUpload>) fileUploadRepository.findAll();
            for(FileUpload fileUp: fileUploads){
                if (fileUp.getFileName() != null && !fileUp.getFileName().isEmpty()) {
                    System.out.println("1: RealImageUrl: " + fileUp.getFileName());
                    StringTokenizer stringTokenizer = new StringTokenizer(fileUp.getFileName(),"/");

                    while (stringTokenizer.hasMoreTokens()) {
                        imgWithEx = stringTokenizer.nextToken();
                    }
                    System.out.println("2: imgWithEx: " + imgWithEx);
                    StringTokenizer stringTokenizer1 = new StringTokenizer(imgWithEx,".");

                    while (stringTokenizer1.hasMoreTokens()){
                        imgCutEx = (String) stringTokenizer1.nextElement();
                        if (stringTokenizer1.hasMoreTokens()) {
                            imgEx = (String) stringTokenizer1.nextElement();
                        }
                    }

                    System.out.println("3: imgCutEx: " + imgCutEx);
                    System.out.println("4: imgEx: " + imgEx);
                }

                if (imageName.equalsIgnoreCase(imgCutEx)) {
                    System.out.println("Equal imgCutEx");
                    if (JPG.equals(imgEx)) {
                        bJpg = true;
                    } else if (PNG.equals(imgEx)) {
                        bPng = true;
                    } else if (JPEG.equals(imgEx)){
                        bJpeg = true;
                    } else if (JPGC.equals(imgEx)){
                        bJPG = true;
                    } else if (PNGC.equals(imgEx)){
                        bPNG = true;
                    } else if(JPEGC.equals(imgEx)){
                        bJPEG = true;
                    }
                }
            }
            if (bJpg == true) {
                f = new File(UPLOADED_FOLDER + imageName + ".jpg"); //image file path
                image = ImageIO.read(f);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", baos);
                media = baos.toByteArray();

                System.out.println("JPG: Reading complete.");
                responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
            } else if(bPng == true){
                f = new File(UPLOADED_FOLDER + imageName + ".png"); //image file path
                image = ImageIO.read(f);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);
                media = baos.toByteArray();

                System.out.println("PNG: Reading complete.");
                responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
            } else if(bJpeg == true){
                f = new File(UPLOADED_FOLDER + imageName + ".jpeg"); //image file path
                image = ImageIO.read(f);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpeg", baos);
                media = baos.toByteArray();

                System.out.println("JPEG: Reading complete.");
                responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
            }else if(bJPG == true){
                f = new File(UPLOADED_FOLDER + imageName + ".JPG"); //image file path
                image = ImageIO.read(f);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "JPG", baos);
                media = baos.toByteArray();

                System.out.println("JPGC: Reading complete.");
                responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
            }else if(bPNG == true){
                f = new File(UPLOADED_FOLDER + imageName + ".PNG"); //image file path
                image = ImageIO.read(f);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "PNG", baos);
                media = baos.toByteArray();

                System.out.println("PNGC: Reading complete.");
                responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
            }else if(bJPEG == true){
                f = new File(UPLOADED_FOLDER + imageName + ".JPEG"); //image file path
                image = ImageIO.read(f);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "JPEG", baos);
                media = baos.toByteArray();

                System.out.println("JPEGC: Reading complete.");
                responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
            responseEntity = new ResponseEntity<>(media, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }*/
    /*update author with image/*/
    /*@ApiOperation(value = "TODO: UPDATE NEW BY ID",response = News.class)
    @RequestMapping(value = "/img/update/{id}/{newsTypeDesEn}/{email}/{authorEmail}", method = RequestMethod.PUT)
    private void update(@RequestParam("file") MultipartFile file,
                        RedirectAttributes redirectAttributes,
                        @Valid News news,
                        @PathVariable("id") Long id,
                        @PathVariable("newsTypeDesEn") String newsTypeDesEn,
                        @PathVariable("email") String email,
                        @PathVariable("authorEmail") String authorEmail) {
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

            NewsType newsType = null;
            SecUser secUser = null;
            Author author = null;

            if (newsTypeDesEn.getBytes() != null) {
                newsType = newsTypeRepository.findByDesEn(newsTypeDesEn);
            }
            if (email.getBytes() != null) {
                secUser = userService.findUserByEmail(email);
            }
            if (authorEmail.getBytes() != null) {
                author = authorRepository.findByEmail(authorEmail + ".com");
            }

            if (file.getOriginalFilename().getBytes() != null) {
                news.setImage(file.getOriginalFilename());
                news.setRealImageUrl(MainURL.URL_IMAGE_MAIN_NEWS + file.getOriginalFilename());
            }

            if (newsType != null) {
                news.setNewsType(newsType);
            }

            if (secUser != null) {
                news.setSecUser(secUser);
            }

            if (author != null) {
                news.setAuthor(author);
            }

            news.setId(id);

            newsRepository.save(news);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    @ApiOperation(value = "TODO: SEARCH NEWS BY NEWS TITLE OR NAME",response = News.class)
    @RequestMapping(value = "/find/{title}", method = RequestMethod.GET)
    private ResponseEntity<?> findByTitle(@PathVariable("title") String title) {
        List<News> newsList = null;
        if (title != null) {
            newsList = (List<News>) newsService.getByName(title);
            if (newsList != null && newsList.size() != 0) {
                return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_GET_ALL_SUCCESS,MessageRout.MS_CODE_SUCCESS,newsList),HttpStatus.OK);
            }
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_GET_ALL_ERROR,MessageRout.MS_CODE_FAULT,newsList),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_INPUT_DATA,MessageRout.MS_CODE_FAULT,newsList),HttpStatus.NOT_FOUND);
        }
    }
}
