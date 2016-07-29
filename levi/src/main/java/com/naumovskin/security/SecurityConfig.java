package com.naumovskin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        
	}
        
//	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.httpBasic().and()
			.authorizeRequests()
//				.antMatchers("/index.html", "/home.html", "/login.html", "/", "/api/ads", "/api/users", "/partial/ads.html").permitAll()
//				.anyRequest().authenticated()
				.anyRequest().permitAll()
				.and()
				
				.logout()
				.logoutUrl("/logout")
				.deleteCookies("remember-me")
				.logoutSuccessUrl("/")
				.permitAll()
				.and()
			.csrf().disable();
//				.csrfTokenRepository(new CookieCsrfTokenRepository());
		// @formatter:on
	}
	
}
