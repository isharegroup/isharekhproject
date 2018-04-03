package com.isharekh.domain.controllers.proxy.news;

import com.isharekh.CustomMessageType;
import com.isharekh.domain.controllers.proxy.NewsMainProxy;
import com.isharekh.domain.models.news.News;
import com.isharekh.routs.MessageRout;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 2/22/2018
*/
@RestController
@RequestMapping(value = "/api/gateway")
@Api(value="newsgateway", description="gateway news api for news app")
public class NewsRestControllerProxy {
    @ApiOperation(value = "TODO: GET NEWS GATEWAY",response = News.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listNewsGetway() {
        List<News> news = (List<News>) NewsMainProxy.listAllNews();
        if (news != null) {
            return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_GET_ALL_SUCCESS, MessageRout.MS_CODE_SUCCESS, news), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new CustomMessageType(MessageRout.MS_GET_ALL_ERROR, MessageRout.MS_CODE_FAULT, news), HttpStatus.NOT_FOUND);
    }
}
