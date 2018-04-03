package com.isharekh.domain.controllers.newstypeandauthor;

import com.isharekh.CustomMessageType;
import com.isharekh.domain.models.authors.Author;
import com.isharekh.domain.models.news.NewsType;
import com.isharekh.domain.repositories.author.AuthorRepository;
import com.isharekh.domain.repositories.news.NewsTypeRepository;
import com.isharekh.routs.MessageRout;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by : Ron Rith
 * Create Date: 02/18/2018.
 */
@RestController
@RequestMapping(value = "/api/newstypeandauthors")
@Api(value = "bindapi",description = "newstype author for news app")
public class NewsTypeAndAuthorRestController {
    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @ApiOperation(value = "TODO: GET ALL BIND NEWS TYPE AND AUTHOR",response = Object.class)
    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity<?> getAllNewsTypeAndAuthor() {
        List<NewsType> newsTypes = (List<NewsType>) newsTypeRepository.findAll();
        List<Author> authors = (List<Author>) authorRepository.findAll();
        HashMap<String, List<?>> newstypeAuthor = new HashMap<String, List<?>>();

        if (newsTypes != null && authors != null) {
            newstypeAuthor = createNewsTypeAuthorDictionary(newsTypes,authors);
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_GET_ALL_SUCCESS,MessageRout.MS_CODE_SUCCESS,newstypeAuthor), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_GET_ALL_ERROR,MessageRout.MS_CODE_FAULT,newstypeAuthor), HttpStatus.NOT_FOUND);
    }

    public HashMap<String,List<?>> createNewsTypeAuthorDictionary(List<NewsType> newsTypes, List<Author> authors){
        System.out.println(newsTypes);
        HashMap<String, List<?>> newstypeAuthor = new HashMap<String, List<?>>();
        newstypeAuthor.put("newstypes",newsTypes);
        newstypeAuthor.put("authors",authors);

        return newstypeAuthor;
    }
}
