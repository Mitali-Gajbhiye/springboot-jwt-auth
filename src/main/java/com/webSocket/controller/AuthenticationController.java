package com.webSocket.controller;

import com.webSocket.dto.JwtAuthenticationReopnse;
import com.webSocket.dto.RefreshTokenRequest;
import com.webSocket.dto.SignUpRequest;
import com.webSocket.dto.SigninRequest;
import com.webSocket.entites.User;
import com.webSocket.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }
    
    
    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationReopnse> signin(@RequestBody SigninRequest signinRequest) throws IllegalAccessException{
    	return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationReopnse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) throws IllegalAccessException{
    	return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
