package com.isharekh.domain.controllers.videos;

import com.isharekh.domain.models.videos.Video;
import com.isharekh.domain.services.videos.AVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 2/28/2018
*/
@RestController
@RequestMapping(value = "/api/videos")
public class AVideoRestController {
    @Autowired
    private AVideoService videoService;

    @RequestMapping(method = RequestMethod.GET)
    private List<Video> getAllVideos() {
        return (List<Video>) videoService.getAll();
    }
}
