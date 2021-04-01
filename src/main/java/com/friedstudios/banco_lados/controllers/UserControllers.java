package com.friedstudios.banco_lados.controllers;

import com.friedstudios.banco_lados.models.entities.UserEntity;
import com.friedstudios.banco_lados.models.repositories.UserRepositories;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControllers {

    private final UserRepositories userRepositories;


    public UserControllers(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @GetMapping("/{dni}")
    public List<UserEntity> getUser(@PathVariable String dni) {
        return userRepositories.findAllByDni(dni);
    }

    @PostMapping("/new")
    public UserEntity newUser(@RequestBody UserEntity userEntity){
        return userRepositories.save(userEntity);
    }


}