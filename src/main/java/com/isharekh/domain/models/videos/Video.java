package com.isharekh.domain.models.videos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isharekh.domain.models.base.BaseEntity;
import com.isharekh.domain.models.authors.Author;
import com.isharekh.domain.models.news.NewsType;
import com.isharekh.domain.models.references.NewsReference;
import com.isharekh.domain.models.user_security.SecUser;

import javax.persistence.*;
import java.util.Set;

/*
Create By: Ron Rith
Create Date: 2/28/2018
*/
@Entity
@Table(name = "td_video")
public class Video extends BaseEntity {
    private String des;

    private String desEn;

    private Integer numView;

    private Integer numShare;

    private String comment;

    private NewsType newsType;

    private SecUser secUser;

    private Video parent;

    private Set<Video> videos;

    private Author author;

    private NewsReference newsReference;

    private String remoteVideoUrl;

    private String localVideo;

    @Column(name = "des",length = 2500)
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Column(name = "des_en",length = 2500)
    public String getDesEn() {
        return desEn;
    }

    public void setDesEn(String desEn) {
        this.desEn = desEn;
    }

    public Integer getNumView() {
        return numView;
    }

    public void setNumView(Integer numView) {
        this.numView = numView;
    }

    public Integer getNumShare() {
        return numShare;
    }

    public void setNumShare(Integer numShare) {
        this.numShare = numShare;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="news_type_id")
    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sec_user_id")
    public SecUser getSecUser() {
        return secUser;
    }

    public void setSecUser(SecUser secUser) {
        this.secUser = secUser;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    public Video getParent() {
        return parent;
    }

    public void setParent(Video parent) {
        this.parent = parent;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "parent")
    public Set<Video> getVideos() {
        return videos;
    }

    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "news_refer_id")
    public NewsReference getNewsReference() {
        return newsReference;
    }

    public void setNewsReference(NewsReference newsReference) {
        this.newsReference = newsReference;
    }

    @Column(name = "remote_video_url")
    public String getRemoteVideoUrl() {
        return remoteVideoUrl;
    }

    public void setRemoteVideoUrl(String remoteVideoUrl) {
        this.remoteVideoUrl = remoteVideoUrl;
    }

    @Column(name = "local_video")
    public String getLocalVideo() {
        return localVideo;
    }

    public void setLocalVideo(String localVideo) {
        this.localVideo = localVideo;
    }
}
