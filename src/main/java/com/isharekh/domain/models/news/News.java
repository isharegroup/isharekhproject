package com.isharekh.domain.models.news;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isharekh.domain.models.base.BaseEntity;
import com.isharekh.domain.models.authors.Author;
import com.isharekh.domain.models.references.NewsReference;
import com.isharekh.domain.models.user_security.SecUser;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by : Ron Rith
 * Create Date: 01/12/2018.
 */
@Entity
/*@NamedQuery(name = "News.findByName", query = "SELECT n FROM News n WHERE LOWER(n.name) = LOWER(?1)")*/
@Table(name = "news")
public class News extends BaseEntity{

    private String name;

    private String dec;

    private String desEn;

    private String image;

    private Integer numView;

    private Integer numShare;

    private String comment;

    private NewsType newsType;

    private SecUser secUser;

    private News parent;

    private Set<News> news;

    private Author author;

    private NewsReference newsReference;

    private String realImageUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Column(name = "des")
    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    @Lob
    @Column(name = "des_en")
    public String getDesEn() {
        return desEn;
    }

    public void setDesEn(String desEn) {
        this.desEn = desEn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Lob
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
    public News getParent() {
        return parent;
    }

    public void setParent(News parent) {
        this.parent = parent;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "parent")
    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
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

    @Column(name = "real_image_url")
    public String getRealImageUrl() {
        return realImageUrl;
    }

    public void setRealImageUrl(String realImageUrl) {
        this.realImageUrl = realImageUrl;
    }
}
