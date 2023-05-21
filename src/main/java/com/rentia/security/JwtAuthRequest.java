package com.rentia.security;

import lombok.*;

@Data
public class JwtAuthRequest {

	private String username;
	
	private String password;
	
}
