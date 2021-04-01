package com.friedstudios.banco_lados.controllers;


import com.friedstudios.banco_lados.models.entities.AccountEntity;
import com.friedstudios.banco_lados.models.repositories.AccountsRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
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
