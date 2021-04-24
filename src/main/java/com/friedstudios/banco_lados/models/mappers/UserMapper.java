package com.friedstudios.banco_lados.models.mappers;

import com.friedstudios.banco_lados.models.dto.NewUserDTO;
import com.friedstudios.banco_lados.models.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity mapUserDTOtoUserEntity(NewUserDTO newUserDTO){
        return new UserEntity(
                newUserDTO.getDni(),
                newUserDTO.getFirstname(),
                newUserDTO.getLastName(),
                newUserDTO.getAdress(),
                newUserDTO.getUsername(),
                newUserDTO.getPassword());


    }

}

