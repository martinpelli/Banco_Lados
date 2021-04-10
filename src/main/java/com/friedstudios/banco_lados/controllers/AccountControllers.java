package com.friedstudios.banco_lados.controllers;


import com.friedstudios.banco_lados.models.entities.AccountEntity;
import com.friedstudios.banco_lados.models.repositories.AccountsRepositories;
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

    private final  AccountsRepositories accountsRepositories;

    public AccountControllers(AccountsRepositories accountsRepositories) {
        this.accountsRepositories = accountsRepositories;
    }

    @GetMapping("/{userId}")
    public List<AccountEntity> getUserAccounts(@PathVariable String userId) {
        return accountsRepositories.findAllByUserId(userId);
    }

    @PostMapping("/new")
    public AccountEntity newAccount(@RequestBody AccountEntity accountEntity){
        return accountsRepositories.save(accountEntity);
    }


}
