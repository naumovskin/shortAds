package com.naumovskin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.naumovskin.model.SystemRole;
import com.naumovskin.model.User;
import com.naumovskin.service.UserService;
import com.naumovskin.web.dto.UserRegistrationDTO;
@Component
public class UserRegistrationDTOToUser implements Converter<UserRegistrationDTO, User>{

	@Autowired
	UserService userService;

	@Override
	public User convert(UserRegistrationDTO dto) {
		User user = new User();

		if (dto.getId() != null) {
			user = userService.findOne(dto.getId());

			if (user == null) {
				throw new IllegalStateException("Tried to " + "modify a non-existant activity");
			}
		}

		user.setId(dto.getId());
		if (dto.getFirstname()!= null){
			user.setFirstname(dto.getFirstname());
		}
		if(dto.getLastname()!= null) {
			user.setLastname(dto.getLastname());
		}
		user.setUsername(dto.getUsername());
		
		if(dto.getPassword().equals(dto.getrPassword())){
			user.setPassword(dto.getPassword());
		}
		
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());
		// setting default system Role for newly registered users
		user.setSystemRole(SystemRole.USER);
		
		return user;
	}

	public List<User> convert(List<UserRegistrationDTO> dtoUsers) {
		List<User> users = new ArrayList<>();

		for (UserRegistrationDTO dto : dtoUsers) {
			users.add(convert(dto));
		}

		return users;
	}

	
	
}
