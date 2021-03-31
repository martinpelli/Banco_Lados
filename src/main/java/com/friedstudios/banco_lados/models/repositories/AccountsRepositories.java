package com.friedstudios.banco_lados.models.repositories;

import com.friedstudios.banco_lados.models.entities.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepositories extends CrudRepository<AccountEntity,Long> {
}
