package com.isharekh.domain.models.user_security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isharekh.domain.models.base.BaseEntity;
import com.isharekh.domain.models.authors.Author;
import com.isharekh.domain.models.news.News;
import com.isharekh.domain.models.news.NewsType;
import com.isharekh.domain.models.user_facebook.SecFBUser;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.Set;

/*
Create By: Ron Rith
Create Date: 1/2/2018
*/
@Entity
@Table(name = "sec_user")
public class SecUser extends BaseEntity{

	private String email;

	private String password;

	private String name;

	private String lastName;

	private String gender;

	private Integer active;

	private String imageName;

	private Set<Role> roles;

	private Set<NewsType> newsType;

	private Set<News> news;

	private Set<Author> authors;

	private String realImageUrl;

	private Set<SecFBUser> secFBUsers;

	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
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

	@Column(name = "active")
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

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public SecUser() {
	}

	@JsonIgnore
	@OneToMany(mappedBy = "secUser")
	public Set<NewsType> getNewsType() {
		return newsType;
	}

	public void setNewsType(Set<NewsType> newsType) {
		this.newsType = newsType;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "secUser")
	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "secUser")
	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	@Column(name = "real_image_url")
	public String getRealImageUrl() {
		return realImageUrl;
	}

	public void setRealImageUrl(String realImageUrl) {
		this.realImageUrl = realImageUrl;
	}

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_facebook", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "facebook_id"))
	public Set<SecFBUser> getSecFBUsers() {
		return secFBUsers;
	}

	public void setSecFBUsers(Set<SecFBUser> secFBUsers) {
		this.secFBUsers = secFBUsers;
	}
}
