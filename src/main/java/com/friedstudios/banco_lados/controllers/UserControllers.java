package com.friedstudios.banco_lados.controllers;

import com.friedstudios.banco_lados.models.dto.NewUserDTO;
import com.friedstudios.banco_lados.models.entities.UserEntity;
import com.friedstudios.banco_lados.models.repositories.UserRepositories;

import com.friedstudios.banco_lados.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControllers {

    private final UsersService usersService;


    public UserControllers(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/{Dni}")
    public UserEntity getUsersByDni(@PathVariable String Dni) {
        return usersService.getUsersByDni(Dni);
    }

    @PostMapping("/new")
    public ResponseEntity<String> newUser(@RequestBody NewUserDTO newUserDTO){
        return usersService.createUser(newUserDTO);
    }



}