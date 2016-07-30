package com.naumovskin;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naumovskin.model.Ad;
import com.naumovskin.model.Category;
import com.naumovskin.model.SystemRole;
import com.naumovskin.model.User;
import com.naumovskin.service.AdService;
import com.naumovskin.service.CategoryService;
import com.naumovskin.service.UserService;
@Component
public class TestData {

	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	AdService adService;
	
	@PostConstruct
	private void init() {
		 for (int i = 1; i <= 20; i++) {
	    	 
			 	User u = new User();
			 	
			 	u.setEmail("email" + i + "@mail.com");
			 	u.setPassword("1" + i);
			 	u.setFirstname("firstname" + i);
			 	u.setLastname("lastname" + i);
			 	u.setPhone("011311" + i);
			 	u.setSystemRole(SystemRole.USER);
			 	u.setUsername("username" + i);

			 	userService.save(u);
			 
			 
	    	   Category c = new Category();
	    	   
	    	   c.setName("Category" + i);
	    	   c.setDescription("Description" + i);
	    	   categoryService.save(c);
	    	  
	    	   Ad a = new Ad();
	    	   
	    	   a.setUser(u);
	    	   //a.setCategory(c);
	    	   a.setCategory(c);
	    	   java.util.Date created = new java.util.Date();
	    	   // timestamp for 2 weeks after posting in unix time
	    	   long expired = created.getTime() + 1296000L * 1000;
	    	   a.setDatePosted(created);
	    	   a.setDescription("description");
	    	   a.setTitle("ad_name" + i);
	    	   Date expiredDate = new Date(expired);
	    	   a.setExpiryDate(expiredDate);
	    	   
	    	   adService.save(a);
	    	   
	    	   
	    	   
	    	   	       }
	}	
	
	
	
	
}



