package com.naumovskin.security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.naumovskin.model.SystemRole;
import com.naumovskin.model.User;

@Service("authorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationService.class);

	@Override
	public boolean isAdmin(UserDetailsImpl userDetails) {
		
		return userDetails != null && userDetails.getSystemRole() == SystemRole.ADMIN;
	}

	@Override
	public boolean canAccessAd(UserDetailsImpl userDetails, User user) {
		if(user == null || userDetails == null){
			return false;
		}else if(user.getId() == null){
			return true;
		}else{
		return canAccessAd(userDetails, user.getId());
		}
	}

	@Override
	public boolean canAccessAd(UserDetailsImpl userDetails, Long id) {
		LOGGER.debug("Checking if user={} has access to user={}", userDetails, id);
		return userDetails != null && (userDetails.getId().equals(id) || isAdmin(userDetails));
	}
	
}
