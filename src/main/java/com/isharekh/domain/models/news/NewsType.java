package com.isharekh.domain.models.news;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isharekh.domain.models.base.BaseEntity;
import com.isharekh.domain.models.user_security.SecUser;

import javax.persistence.*;
import java.util.Set;

/*
Create By: Ron Rith
Create Date: 1/11/2018
*/
@Entity
@Table(name = "news_type")
public class NewsType extends BaseEntity{
    private String des;

    private String desEn;

    private String icon;

    private SecUser secUser;

    private Set<News> news;

    private NewsType newsType;

    private Set<NewsType> newsTypes;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    @Column(name = "des_en")
    public String getDesEn() {
        return desEn;
    }

    public void setDesEn(String desEn) {
        this.desEn = desEn;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sec_user_id")
    public SecUser getSecUser() {
        return secUser;
    }

    public void setSecUser(SecUser secUser) {
        this.secUser = secUser;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "newsType")
    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "parent_id")
    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "newsTypes")
    public Set<NewsType> getNewsTypes() {
        return newsTypes;
    }

    public void setNewsTypes(Set<NewsType> newsTypes) {
        this.newsTypes = newsTypes;
    }
}
