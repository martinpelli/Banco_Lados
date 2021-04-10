package com.friedstudios.banco_lados.controllers;

import com.friedstudios.banco_lados.models.entities.UserEntity;
import com.friedstudios.banco_lados.models.repositories.UserRepositories;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserControllers {

    private final UserRepositories userRepositories;


    public UserControllers(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }



    @PostMapping("/new")
    public UserEntity newUser(@RequestBody UserEntity userEntity){
        return userRepositories.save(userEntity);
    }


}