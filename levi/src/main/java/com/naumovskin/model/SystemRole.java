package com.naumovskin.model;

import java.util.ArrayList;
import java.util.List;

public enum SystemRole {
	ADMIN,USER;
	
	 public static List<String> names() {

	        List<String> list = new ArrayList<String>();
	        for (SystemRole s : values()) {
	            list.add(s.name());
	        }

	        return list;
	    }
}
