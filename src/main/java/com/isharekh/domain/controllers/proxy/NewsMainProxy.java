package com.isharekh.domain.controllers.proxy;

import com.isharekh.domain.models.news.News;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/*
Create By: Ron Rith
Create Date: 2/22/2018
*/
public class NewsMainProxy {
    public static final String URL_REST_NEWS = "https://newiosapi.herokuapp.com/rest/news";

    public static final HttpHeaders getHeaders(){
        String plainCredentials="rithronlkh@gmail.com:123456";
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        //headers.add("Authorization", "Basic " + KEY_AUTHORIZATION);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }
    public static final List<News> listAllNews(){
        System.out.println("\nTesting listAllUsers API-----------");
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<List> response = restTemplate.exchange(URL_REST_NEWS, HttpMethod.GET, request, List.class);
        List<LinkedHashMap<String, Object>> usersMap = (List<LinkedHashMap<String, Object>>)response.getBody();

        List<News> news = new ArrayList<>();

        try {
            if (usersMap != null) {
                for (LinkedHashMap<String, Object> map : usersMap) {
                    System.out.println("news : id=" + map.get("id"));
                    System.out.println("news newsType: "+ map.get("newsType"));
                   /* System.out.println("Logo : dec=" + map.get("dec"));
                    System.out.println("Logo : desEn=" + map.get("desEn"));
                    System.out.println("Logo : name=" + map.get("name"));
                    System.out.println("Logo : realImageUrl=" + map.get("realImageUrl"));*/
                    News news1 = new News();
                    news1.setId(Long.valueOf(map.get("id").toString()));
                    news.add(news1);
                }
                return news;
            } else {
                System.out.println("No user exist----------");
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return news;
    }
}
