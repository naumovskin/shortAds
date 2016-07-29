package com.naumovskin.security;

import org.springframework.security.core.authority.AuthorityUtils;

import com.naumovskin.model.SystemRole;
import com.naumovskin.model.User;

public class UserDetailsImpl extends org.springframework.security.core.userdetails.User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private SystemRole systemRole;
	private String displayName;
	
    public UserDetailsImpl(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getSystemRole().toString()));
        this.id = user.getId();
        this.systemRole = user.getSystemRole();
        setDisplayName(user);
    }

    public Long getId() {
        return id;
    }

    public SystemRole getSystemRole() {
        return systemRole;
    }
    
    public String getDisplayName() {
    	return displayName;
    }
    
    private void setDisplayName(User user) {
    	displayName = user.getUsername();
    }
	
}
