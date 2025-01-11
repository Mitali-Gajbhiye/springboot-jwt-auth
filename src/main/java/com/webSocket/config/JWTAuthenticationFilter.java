package com.webSocket.config;
import com.webSocket.service.JWTService;
import com.webSocket.service.UserService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    private final UserService userService;


	@SuppressWarnings("deprecation")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 final String authHeader = request.getHeader("Authorization");
	        final String jwt;
	        final String userEmail;

	        if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer")){
	          filterChain.doFilter(request, response);
	          return;
	        }
	        jwt = authHeader.substring(7);
	        userEmail = jwtService.ExtractUserName(jwt);
	        if((!StringUtils.isEmpty(userEmail)) && SecurityContextHolder.getContext().getAuthentication() == null){
	            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
	        if(jwtService.isTokenValid(jwt, userDetails)){
	            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
	            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
	                    userDetails, null, userDetails.getAuthorities()
	            );

	            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            securityContext.setAuthentication(token);
	            SecurityContextHolder.setContext(securityContext);
	        }
	        }

	        filterChain.doFilter(request, response);
	    }


}
