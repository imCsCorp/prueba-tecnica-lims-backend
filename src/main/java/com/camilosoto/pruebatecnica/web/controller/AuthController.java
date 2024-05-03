package com.camilosoto.pruebatecnica.web.controller;

import com.camilosoto.pruebatecnica.domain.dto.LoginDTO;
import com.camilosoto.pruebatecnica.domain.dto.UserDTO;
import com.camilosoto.pruebatecnica.domain.service.AuthService;
import com.camilosoto.pruebatecnica.web.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDTO loginDTO) {
        // user validation
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                loginDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(login);
        // jwt
        String jwt = jwtUtil.create(loginDTO.getEmail());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user) {
        UserDTO record = authService.create(user);
        // Jwt
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(user.getEmail(),
                user.getPassword());
        Authentication authentication = authenticationManager.authenticate(login);
        String jwt = jwtUtil.create(user.getEmail());
        // response

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, jwt);
        return new ResponseEntity<>(record, headers, HttpStatus.CREATED);
    }
}
