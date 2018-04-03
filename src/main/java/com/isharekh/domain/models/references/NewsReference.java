package com.isharekh.domain.models.references;

import com.isharekh.domain.models.base.BaseEntity;
import com.isharekh.domain.models.news.News;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/*
Create By: Ron Rith
Create Date: 1/15/2018
*/
@Entity
@Table(name = "news_reference")
public class NewsReference extends BaseEntity {
    private String des;

    private String desen;

    private String location;

    private String phone1;

    private String phone2;

    private String noted;

    private Set<News> news;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDesen() {
        return desen;
    }

    public void setDesen(String desen) {
        this.desen = desen;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getNoted() {
        return noted;
    }

    public void setNoted(String noted) {
        this.noted = noted;
    }

    @OneToMany(mappedBy = "newsReference")
    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }
}
