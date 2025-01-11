package com.webSocket.service.impl;

import com.webSocket.dto.JwtAuthenticationReopnse;
import com.webSocket.dto.RefreshTokenRequest;
import com.webSocket.dto.SignUpRequest;
import com.webSocket.dto.SigninRequest;
import com.webSocket.entites.Role;
import com.webSocket.entites.User;
import com.webSocket.repository.UserRepository;
import com.webSocket.service.AuthenticationService;
import com.webSocket.service.JWTService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    
    private final JWTService jwtService;
    

    @Override
    public User signUp(SignUpRequest signUpRequest){
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getLastName());
        user.setSecondName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

 return userRepository.save(user);
    }
    
   public JwtAuthenticationReopnse signin(SigninRequest signinRequest) throws IllegalAccessException {
	   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
			   signinRequest.getPassword()));
	   
	   var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalAccessException("Invalid input"));
       var jwt = jwtService.generateToken(user);
       var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
   
       JwtAuthenticationReopnse jwtAuthenticationReopnse  = new JwtAuthenticationReopnse();
       
       jwtAuthenticationReopnse.setToken(jwt);
       jwtAuthenticationReopnse.setRefreshToken(refreshToken);
       
       return jwtAuthenticationReopnse;
   
   }
   
   @Override
   public JwtAuthenticationReopnse refreshToken(RefreshTokenRequest refreshTokenRequest) {
	   String userEmail = jwtService.ExtractUserName(refreshTokenRequest.getToken());
	   User user = userRepository.findByEmail(userEmail).orElseThrow();
	   if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
		   
	       var jwt = jwtService.generateToken(user);

		   JwtAuthenticationReopnse jwtAuthenticationReopnse  = new JwtAuthenticationReopnse();
	       
	       jwtAuthenticationReopnse.setToken(jwt);
	       jwtAuthenticationReopnse.setRefreshToken(refreshTokenRequest.getToken());
	       
	       return jwtAuthenticationReopnse;
	   }
	   return null;
   }
}
