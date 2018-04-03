package com.isharekh.domain.models.authors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isharekh.domain.models.base.BaseEntity;
import com.isharekh.domain.models.news.News;
import com.isharekh.domain.models.user_security.SecUser;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by : Ron Rith
 * Create Date: 01/12/2018.
 */
@Entity
@Table(name = "author")
public class Author extends BaseEntity{
    private String email;

    private String name;

    private String lastName;

    private String gender;

    private Integer active;

    private String imageName;

    private SecUser secUser;

    private Set<News> news;

    private String realImageUrl;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Column(name = "image_name")
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sec_user_id")
    public SecUser getSecUser() {
        return secUser;
    }

    public void setSecUser(SecUser secUser) {
        this.secUser = secUser;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    @Column(name = "real_image_url")
    public String getRealImageUrl() {
        return realImageUrl;
    }

    public void setRealImageUrl(String realImageUrl) {
        this.realImageUrl = realImageUrl;
    }
}
