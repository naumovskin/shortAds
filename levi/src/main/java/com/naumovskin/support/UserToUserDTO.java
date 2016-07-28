package com.naumovskin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.naumovskin.model.User;
import com.naumovskin.web.dto.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {
	
	@Override
	public UserDTO convert(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());
//		for(int i = 0; i < user.getMyAdverts().size(); i++){
//			userDTO.g.add(user.getAds().get(i));
//		}
		return userDTO;
	}
	
	public List<UserDTO> convert(List<User> users){
		
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for(int i = 0; i < users.size(); i++){
			usersDTO.add(convert(users.get(i)));
		}
		
		return usersDTO;
		
	}
	
}
