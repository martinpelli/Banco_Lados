package com.friedstudios.banco_lados.models.mappers;

import com.friedstudios.banco_lados.models.dto.NewAccountDTO;
import com.friedstudios.banco_lados.models.entities.AccountEntity;
import org.springframework.stereotype.Component;


@Component
public class AccountMapper {

    public AccountEntity mapAccountDTOtoAccountEntity(NewAccountDTO newAccountDTO){
        return new AccountEntity(
                newAccountDTO.getNumber(),
                newAccountDTO.getCbu(),
                newAccountDTO.getType(),
                newAccountDTO.getUserId());


    }
}
