package com.friedstudios.banco_lados.services;

import com.friedstudios.banco_lados.exceptions.BadRequestException;
import com.friedstudios.banco_lados.models.dto.NewUserDTO;
import com.friedstudios.banco_lados.models.entities.AccountEntity;
import com.friedstudios.banco_lados.models.entities.UserEntity;
import com.friedstudios.banco_lados.models.mappers.UserMapper;
import com.friedstudios.banco_lados.models.repositories.UserRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UserMapper userMapper;
    private final UserRepositories userRepositories;

    public UsersService(UserMapper userMapper, UserRepositories userRepositories) {
        this.userMapper = userMapper;
        this.userRepositories = userRepositories;
    }

    public ResponseEntity createUser(NewUserDTO newUserDTO){
        if (userRepositories.findByDni(newUserDTO.getDni()) != null){
            throw new BadRequestException("Error al crear el usuario. El Dni ya existe");
        }

        try {
            UserEntity userEntity = userMapper.mapUserDTOtoUserEntity(newUserDTO);
            userRepositories.save(userEntity);
            return new ResponseEntity<>("Usuario Creado", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Algo salió mal", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
