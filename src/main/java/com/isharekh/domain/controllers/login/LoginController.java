package com.isharekh.domain.controllers.login;

import com.isharekh.domain.models.authors.Author;
import com.isharekh.domain.models.news.News;
import com.isharekh.domain.models.news.NewsType;
import com.isharekh.domain.models.user_security.Role;
import com.isharekh.domain.models.user_security.SecUser;
import com.isharekh.domain.repositories.author.AuthorRepository;
import com.isharekh.domain.repositories.news.NewsRepository;
import com.isharekh.domain.repositories.news.NewsTypeRepository;
import com.isharekh.domain.repositories.user.RoleRepository;
import com.isharekh.domain.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/*
Create By: Ron Rith
Create Date: 1/2/2018
*/
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private NewsTypeRepository newsTypeRepository;

	@Autowired
	private NewsRepository newsRepository;

	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		List<Role> roles = (List<Role>) roleRepository.findAll();
		List<SecUser> secUsers = userService.findAllUsers();

		if (roles == null || roles.size() <= 0) {
			Role role = new Role();
			role.setRole("ADMIN");
			roleRepository.save(role);
		}

		if (secUsers == null || secUsers.size() < 3) {
			isSaveDefaultUser();
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}


	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		SecUser secUser = new SecUser();
		modelAndView.addObject("secUser", secUser);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid SecUser secUser, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		SecUser secUserExists = userService.findUserByEmail(secUser.getEmail());
		if (secUserExists != null) {
			bindingResult
					.rejectValue("email", "error.secUser",
							"There is already a secUser registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(secUser);
			modelAndView.addObject("successMessage", "SecUser has been registered successfully");
			modelAndView.addObject("secUser", new SecUser());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public ModelAndView home(ModelMap modelMap){
		modelMap.put("message",this.message);

		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SecUser secUser = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + secUser.getName() + " " + secUser.getLastName() + " (" + secUser.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/admin");
		return modelAndView;
	}

	private boolean isSaveDefaultUser() {
		List<SecUser> secUsersDefaul = getDefaultUser();
		for (SecUser secUser : secUsersDefaul) {
			userService.saveUser(secUser);
		}
		return true;
	}

	private List<SecUser> getDefaultUser(){
		List<SecUser> secUsers = new ArrayList<>();
		SecUser secUser = new SecUser();
		SecUser secUserUser = new SecUser();
		SecUser secUserAdmin = new SecUser();

		secUser.setName("Rith");
		secUser.setLastName("Ron");
		secUser.setEmail("rithronlkh@gmail.com");
		secUser.setActive(1);
		secUser.setPassword("123456");

		secUserUser.setName("User");
		secUserUser.setLastName("User");
		secUserUser.setEmail("user@gmail.com");
		secUserUser.setActive(1);
		secUserUser.setPassword("123456");

		secUserAdmin.setName("Admin");
		secUserAdmin.setLastName("Admin");
		secUserAdmin.setEmail("admin@gmail.com");
		secUserAdmin.setActive(1);
		secUserAdmin.setPassword("123456");

		secUsers.add(secUser);
		secUsers.add(secUserUser);
		secUsers.add(secUserAdmin);

		//save default author
		isSaveDefaultAuthor(secUser);
		//save default newstype
		isSaveDefaultNewsType(secUserUser);
		//save default news
		isDefaultNewses(secUserUser,getNewsType(),getAuthor());

		return secUsers;
	}
	private boolean isSaveDefaultAuthor(SecUser secUser){
		List<Author> authors = getDefaultAuthors(secUser);
		for(Author author: authors){
			authorRepository.save(author);
		}
		return true;
	}
	private List<Author> getDefaultAuthors(SecUser secUser){
		List<Author> authors = new ArrayList<>();

		Author author = new Author();
		Author author2 = new Author();
		Author author3 = new Author();

		author.setName("Author1");
		author.setEmail("author1@gmail.com");
		author.setActive(1);
		author.setGender("Male");
		author.setObjectStatus(Boolean.TRUE);
		author.setSecUser(secUser);

		author2.setName("Author2");
		author2.setEmail("author2@gmail.com");
		author2.setActive(1);
		author2.setGender("Male");
		author2.setObjectStatus(Boolean.TRUE);
		author2.setSecUser(secUser);

		author3.setName("Author3");
		author3.setEmail("author3@gmail.com");
		author3.setActive(1);
		author3.setGender("Male");
		author3.setObjectStatus(Boolean.TRUE);
		author3.setSecUser(secUser);

		authors.add(author);
		authors.add(author2);
		authors.add(author3);

		return authors;
	}

	private boolean isSaveDefaultNewsType(SecUser secUser) {
		List<NewsType> newsTypes = getDefaultNewsTypes(secUser);
		for (NewsType newsType : newsTypes) {
			newsTypeRepository.save(newsType);
		}
		return true;
	}
	private List<NewsType> getDefaultNewsTypes(SecUser secUser){
		List<NewsType> newsTypes = new ArrayList<>();

		NewsType newsType = new NewsType();
		NewsType newsType2 = new NewsType();
		NewsType newsType3 = new NewsType();

		newsType.setDes("ទំព័រដើម");
		newsType.setDesEn("Home");
		newsType.setSecUser(secUser);

		newsType2.setDes("គំនិតអ្នកមាន");
		newsType2.setDesEn("Rich Man Idea");
		newsType2.setSecUser(secUser);

		newsTypes.add(newsType);
		newsTypes.add(newsType2);

		return newsTypes;
	}

	private boolean isDefaultNewses(SecUser secUser, NewsType newsType, Author author) {
		List<News> newsList = getDefaultNewses(secUser, newsType, author);
		for (News news : newsList) {
			newsRepository.save(news);
		}
		return true;
	}

	private List<News> getDefaultNewses(SecUser secUser,NewsType newsType,Author author){
		List<News> newsList = new ArrayList<>();

		News news = new News();
		News news2 = new News();
		News news3 = new News();

		news.setSecUser(secUser);
		news.setNewsType(newsType);
		news.setAuthor(author);
		news.setName("Khmer");
		news.setDec("Hello World");
		news.setDesEn("Hello worldHello worldHello worldHello worldHello worldHello world");
		news.setRealImageUrl("https://www.visit-angkor.org/wp-content/uploads/2013/01/Khmer-Portrait.jpg");

		news2.setSecUser(secUser);
		news2.setNewsType(newsType);
		news2.setAuthor(author);
		news2.setName("Khmer2");
		news2.setDec("Welcome");
		news2.setDesEn("WelcomeWelcome WelcomeWel comeWelcome WelcomeWelcomeWelcomeWelcomeWelcomeWelcomeWelcomeWelcomeWelcomeWelcomeWelcomeWelcomeWelcome");
		news2.setRealImageUrl("http://cambodiatravel.indochinacharm.com/wp-content/uploads/2016/08/angkor-wat-temple-cambodia.jpg");

		news3.setSecUser(secUser);
		news3.setNewsType(newsType);
		news3.setAuthor(author);
		news3.setName("Khmer");
		news3.setDec("NewsTest");
		news3.setDesEn("NewsTest NewsTest NewsTest NewsTest NewsTestNewsTestNewsTestNewsTestNewsTestNewsTestNewsTestNewsTestNewsTestNewsTestNewsTestNewsTest");
		news3.setRealImageUrl("http://www.khemaradaily.com/wp-content/uploads/2016/09/14215525_308095452883723_955438811_o.jpg");

		newsList.add(news);
		newsList.add(news2);
		newsList.add(news3);

		return newsList;
	}

	/*
 * @Param getAuthor
 * */
	private Author getAuthor(){
		List<Author> authors = (List<Author>) authorRepository.findAll();
		Author author = new Author();
		for(Author autho: authors){
			author = autho;
		}
		return author;
	}

	private NewsType getNewsType(){
		List<NewsType> newsTypes = (List<NewsType>) newsTypeRepository.findAll();
		NewsType newsType = new NewsType();
		for(NewsType newsType1: newsTypes){
			newsType = newsType1;
		}
		return newsType;
	}
	

}
