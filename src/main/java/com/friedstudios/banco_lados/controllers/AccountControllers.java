package com.friedstudios.banco_lados.controllers;


import com.friedstudios.banco_lados.models.dto.NewAccountDTO;
import com.friedstudios.banco_lados.models.entities.AccountEntity;
import com.friedstudios.banco_lados.services.AccountsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountControllers {

    private final AccountsService accountsService;

    public AccountControllers(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping("/{userId}")
    public List<AccountEntity> getAccountsByUserId(@PathVariable String userId) {
        return accountsService.getAccountsByUserId(userId);
    }

    @PostMapping("/new")
    public ResponseEntity<String> newAccount(@RequestBody NewAccountDTO newAccountDTO){
        return accountsService.createAccount(newAccountDTO);
    }


}
