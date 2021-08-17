package com.friedstudios.banco_lados.models.repositories;

import com.friedstudios.banco_lados.models.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepositories extends CrudRepository<UserEntity,Long> {
   UserEntity findByDni(String dni);
   Optional<UserEntity> findByUsername(String username);

}
