package com.webSocket.service;


import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

     String generateToken(UserDetails userDetails);
     String ExtractUserName(String token);
     boolean isTokenValid(String token, UserDetails userDetails);
     String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
