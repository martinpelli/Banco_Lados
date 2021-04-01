package com.friedstudios.banco_lados.controllers;


import com.friedstudios.banco_lados.models.entities.TransactionEntity;
import com.friedstudios.banco_lados.models.repositories.TransactionRepositories;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionControllers {

    private final TransactionRepositories transactionRepositories;


    public TransactionControllers(TransactionRepositories transactionRepositories) {
        this.transactionRepositories = transactionRepositories;
    }

    @GetMapping("/{id}")
    public List<TransactionEntity> getTransaction(@PathVariable BigInteger id) {
        return transactionRepositories.findAllById(id);
    }

    @PostMapping("/new")
    public TransactionEntity newAccount(@RequestBody TransactionEntity transactionEntity){
        return transactionRepositories.save(transactionEntity);
    }


}
