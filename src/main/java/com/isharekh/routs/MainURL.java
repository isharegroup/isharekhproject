package com.isharekh.routs;

/*
Create By: Ron Rith
Create Date: 1/18/2018
*/
public class MainURL {
    //controllers
    public static final String USERS = "/users";
    public static final String NEWSES = "/newses";
    //rest controllers
    public static final String IMG = "/img";
    public static final String REST_SLASH = "/";
    public static final String REST_ID = "/{id}";
    /*users*/
    public static final String REST_USERS = "/api/users";
    public static final String REST_IMG_NAME = "/img/{imageName}";
    public static final String REST_USERS_IMG_CREATE = "/img/create";
    public static final String REST_USERS_IMG_UPDATE = "/img/update/{id}";
    public static final String REST = "/api";
    public static final String TEST = "/tests";
    /*news*/
    public static final String REST_NEWS = "/api/news";
    public static final String REST_NEWS_SAVE = "/save/{email}/{authorEmail}";
    public static final String REST_NEWS_UPDATE = "/{id}/{email}/{authorEmail}";
    public static final String REST_NEWS_SAVE_AUTHOR = "/img/create/{email}/{authorEmail}";
    public static final String REST_NEWS_IMG_UPDATE = "/img/update/{id}/{email}/{authorEmail}";
    /*newstype*/
    public static final String REST_NEWS_TYPE = "/api/newstype";
    public static final String REST_NEWS_TYPE_SAVE_MAIL = "/save/{email}";
    public static final String REST_NEWS_TYPE_UPDATE_MAIL = "/{id}/{email}";
    public static final String REST_NEWS_TYPE_IMG_CREATE = "/img/create/{email}";
    public static final String REST_NEWS_TYPE_IMG_UPDATE = "/img/update/{id}/{email}";
    /*author*/
    public static final String REST_AUTHOR = "/api/authors";
    public static final String REST_AUTHOR_SAVE = "/save/{userEmail}";
    public static final String REST_AUTHOR_UPDATE = "/{id}/{userEmail}";
    public static final String REST_AUTHOR_IMG_CREATE = "/img/create/{userEmail}";
    public static final String REST_AUTHOR_IMG_UPDATE = "/img/update/{id}/{userEmail}";
    /*url image*/
    public static final String REST_UPLOAD = "/api/uploadfile";
    public static final String URL_HOST = "http://localhost:8080";
    //public static final String URL_HOST_IMAGE_LOCAL = "http://localhost:8080/rest/uploadfile/img/";
    public static final String URL_IMAGE_MAIN_LOCAL = URL_HOST + REST_UPLOAD + IMG + REST_SLASH;
    /*remote*/
    public static final String URL_HOST_REMOTE = "https://isharekh.herokuapp.com";
    public static final String URL_IMAGE_MAIN_NEWS = URL_HOST_REMOTE + REST_NEWS + IMG + REST_SLASH;
    public static final String URL_IMAGE_MAIN_AUTHOR = URL_HOST_REMOTE + REST_AUTHOR + IMG + REST_SLASH;
    public static final String URL_IMAGE_MAIN_SECURITY_USERS = URL_HOST_REMOTE + REST_UPLOAD + IMG + REST_SLASH;
    public static final String URL_IMAGE_MAIN_REMOTE = URL_HOST_REMOTE + REST_UPLOAD + IMG + REST_SLASH;
    /*upload folders*/
    /*facebook*/
    public static final String REST_USERS_FB = "/api/facebook";
}
