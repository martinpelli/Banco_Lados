package com.friedstudios.banco_lados.services;

import com.friedstudios.banco_lados.models.entities.UserEntity;
import com.friedstudios.banco_lados.models.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositories userRepositories;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepositories.findByUsername(username);
        if (userEntityOptional.isEmpty()){
            throw new UsernameNotFoundException("Not Found: " + username);

        }
        UserEntity userEntity = userEntityOptional.get();
        return new User(userEntity.getUsername(),userEntity.getPassword(),new ArrayList<>());
    }
}
