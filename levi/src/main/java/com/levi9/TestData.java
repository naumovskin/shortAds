package com.levi9;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.levi9.model.Ad;
import com.levi9.model.Category;
import com.levi9.model.User;
import com.levi9.service.AdService;
import com.levi9.service.CategoryService;
import com.levi9.service.UserService;

@Component
public class TestData {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	AdService adService;
	
	@Autowired
	UserService userService;

	@PostConstruct
	private void init() {
		 for (int i = 1; i <= 20; i++) {
	    	 
			 	User u = new User();
			 	
			 	u.setEmail("email" + i + "@mail.com");
			 	u.setPassword("1" + i);
			 	u.setPhoneNumber("011311" + i);
			 	u.setUsername("username" + i);

			 	userService.save(u);
			 
			 
	    	   Category c = new Category();
	    	   
	    	   c.setName("Category" + i);
	    	   c.setDescription("Description" + i);
	    	   categoryService.save(c);
	    	  
	    	   Ad a = new Ad();
	    	   
	    	   a.setAuthorUsername(u.getUsername());
	    	   //a.setCategory(c);
	    	   a.setCategoryName(c.getName());
	    	   java.util.Date created = new java.util.Date();
	    	   
	    	   a.setCreated(created);
	    	   a.setName("ad_name" + i);
	    	   a.setPassed(created);
	    	   
	    	   adService.save(a);
	    	   
	    	   
	    	   
	    	   	       }
	}	
}
