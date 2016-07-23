package com.levi9.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.levi9.model.User;
import com.levi9.service.UserService;
import com.levi9.web.dto.UserDTO;
@Component
public class UserDTOToUser implements Converter<UserDTO, User>{

	@Autowired
	UserService userService;

	@Override
	public User convert(UserDTO dto) {
		User user = new User();

		if (dto.getId() != null) {
			user = userService.findOne(dto.getId());

			if (user == null) {
				throw new IllegalStateException("Tried to " + "modify a non-existant activity");
			}
		}

		user.setId(dto.getId());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		user.setPhoneNumber(dto.getPhoneNumber());
		
		return user;
	}

	public List<User> convert(List<UserDTO> dtoUsers) {
		List<User> users = new ArrayList<>();

		for (UserDTO dto : dtoUsers) {
			users.add(convert(dto));
		}

		return users;
	}

	
	
}
