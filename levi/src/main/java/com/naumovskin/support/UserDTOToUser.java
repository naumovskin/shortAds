package com.naumovskin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.naumovskin.model.User;
import com.naumovskin.service.UserService;
import com.naumovskin.web.dto.UserDTO;
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
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());
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
