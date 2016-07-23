package com.levi9.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.levi9.model.User;
import com.levi9.web.dto.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {

	
	
	@Override
	public UserDTO convert(User user) {
		if (user == null) {
			return null;
		}

		UserDTO dto = new UserDTO ();

		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setEmail(user.getEmail());
		dto.setPhoneNumber(user.getPhoneNumber());

		return dto;
	}

	public List<UserDTO> convert(List<User> users) {
		List<UserDTO> ret = new ArrayList<>();
			for (User u : users) {
				ret.add(convert(u));
			}
		return ret;
	}

}
