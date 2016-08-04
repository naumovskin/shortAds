package com.naumovskin.security;

import com.naumovskin.model.User;

public interface AuthorizationService {
	
	boolean isAdmin(UserDetailsImpl userDetails);
	
	boolean canAccessAd(UserDetailsImpl userDetails, User user);
	
	boolean canAccessAd(UserDetailsImpl userDetails, Long id);
	
}
