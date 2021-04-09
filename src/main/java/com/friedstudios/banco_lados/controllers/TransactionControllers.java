package com.friedstudios.banco_lados.controllers;


import com.friedstudios.banco_lados.models.dto.NewTransactionDTO;
import com.friedstudios.banco_lados.models.dto.TransactionsDTO;
import com.friedstudios.banco_lados.models.entities.TransactionEntity;
import com.friedstudios.banco_lados.models.repositories.TransactionRepositories;
import com.friedstudios.banco_lados.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/transactions")
public class TransactionControllers {


    private final TransactionService transactionService;


    public TransactionControllers(TransactionService transactionService) {

        this.transactionService = transactionService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity <TransactionsDTO> getTransactionsForAccount(@PathVariable Long accountNumber){

        return new ResponseEntity<>(transactionService.getTransactions(accountNumber), HttpStatus.NOT_FOUND) ;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createTransaction(@RequestBody NewTransactionDTO newTransactionDTO){
        return transactionService.createTransaction(newTransactionDTO);
    }


}
