package com.digital_medical.DigitalMedical.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.digital_medical.DigitalMedical.user.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationFilter(AuthenticationManager authenticationManager){
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        log.info("Username is {}",  email);
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
//        return authenticationManager.authenticate(authenticationToken);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        UserEntity user = (UserEntity)authentication.getPrincipal();
//        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//        String access_token = JWT.create()
//                .withSubject(user.getEmail())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
//                .withIssuer(request.getRequestURL().toString())
//                .sign(algorithm);
//        String refresh_token = JWT.create()
//                .withSubject(user.getEmail())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
//                .withIssuer(request.getRequestURL().toString())
//                .sign(algorithm);
//        response.setHeader("access_token", access_token);
//        response.setHeader("refresh_token", refresh_token);
//    }
}
