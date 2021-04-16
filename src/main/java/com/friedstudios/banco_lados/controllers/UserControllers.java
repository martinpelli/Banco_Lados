package com.friedstudios.banco_lados.controllers;

import com.friedstudios.banco_lados.models.dto.NewAccountDTO;
import com.friedstudios.banco_lados.models.dto.NewUserDTO;
import com.friedstudios.banco_lados.models.repositories.UserRepositories;

import com.friedstudios.banco_lados.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserControllers {

    private final UserRepositories userRepositories;
    private final UsersService usersService;


    public UserControllers(UserRepositories userRepositories, UsersService usersService) {
        this.userRepositories = userRepositories;
        this.usersService = usersService;
    }



    @PostMapping("/new")
    public ResponseEntity<String> newUser(@RequestBody NewUserDTO newUserDTO){
        return usersService.createUser(newUserDTO);
    }



}