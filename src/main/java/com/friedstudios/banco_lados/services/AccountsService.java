package com.friedstudios.banco_lados.services;


import com.friedstudios.banco_lados.exceptions.BadRequestException;
import com.friedstudios.banco_lados.models.dto.NewAccountDTO;
import com.friedstudios.banco_lados.models.entities.AccountEntity;
import com.friedstudios.banco_lados.models.mappers.AccountMapper;
import com.friedstudios.banco_lados.models.repositories.AccountsRepositories;
import com.friedstudios.banco_lados.models.repositories.UserRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

    private final AccountsRepositories accountsRepositories;
    private final AccountMapper accountMapper;
    private final UserRepositories userRepositories;

    public AccountsService(AccountsRepositories accountsRepositories, AccountMapper accountMapper, UserRepositories userRepositories) {
        this.accountsRepositories = accountsRepositories;
        this.accountMapper = accountMapper;
        this.userRepositories = userRepositories;
    }

    public ResponseEntity createAccount(NewAccountDTO newAccountDTO) {

       if (accountsRepositories.findByCbu(newAccountDTO.getCbu()) != null){
           throw new BadRequestException("Error al crear la cuenta. El CBU ya existe");
        }

       if ( userRepositories.findByDni(newAccountDTO.getUserId()) == null){
           throw new BadRequestException("Error al crear la cuenta. El id no coincide con ningún dni de usuario");
       }

        try {
            AccountEntity accountEntity = accountMapper.mapAccountDTOtoAccountEntity(newAccountDTO);
            accountsRepositories.save(accountEntity);
            return new ResponseEntity<>("Cuenta Creada", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Algo salió mal", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
