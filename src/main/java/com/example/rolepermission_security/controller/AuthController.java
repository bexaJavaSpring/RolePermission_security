package com.example.rolepermission_security.controller;

import com.example.rolepermission_security.dto.LoginDto;
import com.example.rolepermission_security.service.AuthService;
import com.example.rolepermission_security.service.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto dto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));
        System.out.println(authenticate.getAuthorities());
        System.out.println(authenticate.getPrincipal());
        return ResponseEntity.ok().body("Tixzimga xush kelibsiz=>"+authenticate.getName());
    }
    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDto dto){
        String register=authService.register(dto);
        return ResponseEntity.ok().body(register);
    }

}
