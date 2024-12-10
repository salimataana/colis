package com.github.ana.deliverymanagement.controllers;

import com.github.ana.deliverymanagement.dto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v1")
public class AuthControllerApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin("http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //String jwt = jwtUtil.generateJwtToken(authentication);
      if(  authentication.isAuthenticated()){
          return ResponseEntity.ok("{\"token\":\"ok\"}");
      }
       return new  ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}


