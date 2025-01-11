package com.webSocket.dto;

import lombok.Data;

@Data
public class JwtAuthenticationReopnse {

	private String token;
	private String refreshToken;
}
