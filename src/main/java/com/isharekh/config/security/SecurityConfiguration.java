package com.isharekh.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/*
Create By: Ron Rith
Create Date: 1/2/2018
*/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/hello/**","/registration/**")
				.permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/admin").permitAll()
				.usernameParameter("email")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied")
				.and()
				.logout().permitAll()
				.and()
				.httpBasic()
				.and()
				.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**","/images/**","/css/**"
				   ,"/build/**","/dist/**","/documentation/**","/js/**","/plugins/**","/ui_admin/**"
				   ,"/bower_components/**","/bootstrap-less/**","/grunt/**","/less/**","/bootstrap/**"
				   ,"/bootstrap-wysihtml5/**","/jquery-slimscroll/**","/fastclick/**","/lib/**","/ui/**"
				   ,"/moment/**","/min/**","/jquery-knob/**","/jvectormap/**","/jquery/**","/jquery-ui/**"
				   ,"/img/**","/jquery.js/**","/bootstrap.css/**","/bootstrap-theme.css/**","/raphael/**"
				   ,"/Ionicons/**","/bootstrap-daterangepicker/**","/datatables.net-bs/**","/datatables.net/**"
				   ,"/datatables.net-bs/**","/editors/**","/fronts/**","/layouts/**"
		   );
	}

	public SecurityConfiguration(DataSource dataSource, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.dataSource = dataSource;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
}