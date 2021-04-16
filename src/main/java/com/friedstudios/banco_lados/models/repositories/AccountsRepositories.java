package com.friedstudios.banco_lados.models.repositories;

import com.friedstudios.banco_lados.models.entities.AccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountsRepositories extends CrudRepository<AccountEntity,Long> {
    List<AccountEntity> findAllByUserId(String userId);
    AccountEntity findByNumber(Long number);
    AccountEntity findByCbu(String cbu);
}
