package com.friedstudios.banco_lados.models.repositories;

import com.friedstudios.banco_lados.models.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface TransactionRepositories extends CrudRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAllById(BigInteger id);

    List<TransactionEntity> findAllByOriginOrDestination(String accountNumber, String accountNumber1);
}
