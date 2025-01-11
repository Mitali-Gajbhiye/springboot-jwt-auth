package com.webSocket.service;

import com.webSocket.dto.JwtAuthenticationReopnse;
import com.webSocket.dto.RefreshTokenRequest;
import com.webSocket.dto.SignUpRequest;
import com.webSocket.dto.SigninRequest;
import com.webSocket.entites.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    
    JwtAuthenticationReopnse signin(SigninRequest signinRequest) throws IllegalAccessException;

    JwtAuthenticationReopnse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
