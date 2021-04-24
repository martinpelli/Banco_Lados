package com.friedstudios.banco_lados.controllers;

import com.friedstudios.banco_lados.exceptions.NotAuthorizedException;
import com.friedstudios.banco_lados.models.dto.AuthenticateRequest;
import com.friedstudios.banco_lados.models.dto.AuthenticationResponse;
import com.friedstudios.banco_lados.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class AuthenticateController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticateRequest authenticateRequest){
        UserDetails userDetails;
        try{
            userDetails = userDetailsService.loadUserByUsername(authenticateRequest.getUsername());
        } catch(UsernameNotFoundException e){
            throw new NotAuthorizedException("Username or password are incorrect");
        }
        if(Objects.isNull(userDetails) || !userDetails.getPassword().equals(authenticateRequest.getPassword())){
            throw  new NotAuthorizedException("El usuario o la contraseña son incorrectos.");

        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(),authenticateRequest.getPassword())
        );

        String jwt = "Bearer " + jwtUtil.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse(jwt);

        return ResponseEntity.ok(response);

    }
}
