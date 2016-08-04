package com.naumovskin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.naumovskin.model.User;
import com.naumovskin.service.UserService;
import com.naumovskin.support.UserDTOToUser;
import com.naumovskin.support.UserRegistrationDTOToUser;
import com.naumovskin.support.UserToUserDTO;
import com.naumovskin.web.dto.UserDTO;
import com.naumovskin.web.dto.UserRegistrationDTO;

@RestController
@RequestMapping(value = "/api/users")
public class ApiUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDTOToUser toUser;

	@Autowired
	private UserToUserDTO toDTO;
	
	@Autowired
	private UserRegistrationDTOToUser rDTOToUser;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<UserDTO>> getUsers() {// promenjena paginacija na false


		List<User> users = userService.findAll();

		return new ResponseEntity<>(toDTO.convert(users), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<UserDTO> getUsers(@PathVariable Long id) {
		User u = userService.findOne(id);
		if (u == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(u), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		User deleted = userService.delete(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(value = "/register",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> add(@RequestBody UserRegistrationDTO newUser) {

		User savedUser = userService.save(rDTOToUser.convert(newUser));

		return new ResponseEntity<>(toDTO.convert(savedUser), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<UserDTO> edit(@RequestBody UserRegistrationDTO u, @PathVariable Long id) {

		System.out.println(u);
		
		if (id != u.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User persisted = userService.save(rDTOToUser.convert(u));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
