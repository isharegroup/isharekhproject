package com.isharekh.domain.services.videos.impl;

import com.isharekh.domain.models.videos.Video;
import com.isharekh.domain.repositories.video.VideoRepository;
import com.isharekh.domain.services.videos.AVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
Create By: Ron Rith
Create Date: 2/28/2018
*/
@Service
@Transactional
public class AVideoServiceImpl implements AVideoService{
    @Autowired
    private VideoRepository videoRepository;

    static String des = null;
    static  String desEn = null;
    static Integer numView = null;
    static Integer numShare = null;
    static String comment = null;

    @Override
    public List getAll() {
        List<Video> videos = (List<Video>) videoRepository.findAll();
        if (videos == null || videos.size() <= 3) {
            for (int i = 0; i < 3; i++) {
                Video video = new Video();
                video.setAuthor(null);
                video.setNewsType(null);
                video.setSecUser(null);
                video.setDes("វីដីអូខ្ញុំ   " + i);
                video.setDesEn("DesEn video DesEn videoDesEn" +
                        "videoDesEn videoDesEn videoDesEn videoDesEn" +
                        "DesEn videoDesEn videoDesEn videoDesEn" +
                        "DesEn videoDesEn videoDesEn videoDesEn videoDesEn videoDesEn video" +
                        "videoDesEn videoDesEn videoDesEn video" + i);
                video.setRemoteVideoUrl("//www.youtube.com/embed/tgbNymZ7vqY");
                videoRepository.save(video);
                videos = (List<Video>) videoRepository.findAll();
            }
        }
        return videos;
    }

    @Override
    public void save(Object video) {
        Video videoOb = new Video();

        if (video != null) {
            videoOb = (Video) video;
            videoRepository.save(videoOb);
        }
    }

    @Override
    public List getAllTShort() {
        List<Video> videos = (List<Video>) videoRepository.findAll();
        List<Video> videos1 = new ArrayList<>();

        for (int i = 0; i < videos.size(); i++) {
            Video video = new Video();
            String stKh = "";
            String stEn = "";
            String stKhc = "";
            String stEnc = "";

            int stKhLength = videos.get(i).getDes().length();
            int stEnLength = videos.get(i).getDesEn().length();

            if (stKhLength > 15 && stEnLength>15) {
                for (int j = 0; j < 15; j++) {
                    stKh += videos.get(i).getDes().charAt(j);
                    stEn += videos.get(i).getDesEn().charAt(j);
                }
            } else {
                for (int j = 0; j < 5; j++) {
                    stKh += videos.get(i).getDes().charAt(j);
                    stEn += videos.get(i).getDesEn().charAt(j);
                }
            }

            video.setDes(stKh);
            video.setDesEn(stEn);
            video.setId(videos.get(i).getId());
            video.setRemoteVideoUrl(videos.get(i).getRemoteVideoUrl()!=null ? videos.get(i).getRemoteVideoUrl() : "");
            video.setComment(videos.get(i).getComment() != null ? videos.get(i).getComment() : "");
            video.setNumView(videos.get(i).getNumView() != null ? videos.get(i).getNumView() : 0);
            video.setNumShare(videos.get(i).getNumShare() != null ? videos.get(i).getNumShare() : 0);

            stKhc = "";
            stEnc = "";
            stKh = "";
            stEn = "";

            videos1.add(video);
        }

        return videos1;
    }

    @Override
    public Object getById(Long id) {
        Video video = null;
        if (id != null) {
            video = videoRepository.findOne(id);
        }

        return video;
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            videoRepository.delete(id);
        }
    }

    @Override
    public void update(Object o, Long id) {
        if (id != null) {
            Video videoObj = new Video();
            videoObj = (Video) o;
            videoObj.setId(id);
            videoRepository.save(videoObj);
        }
    }

}
