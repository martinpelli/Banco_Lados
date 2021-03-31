package com.friedstudios.banco_lados.models.entities;

import com.friedstudios.banco_lados.models.enums.AccountType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "account")
public class AccountEntity {
    @Id
    private Long number;
    private String cbu;
    @Enumerated(EnumType.STRING)
    private AccountType type;

    public AccountEntity(){

    }
    public AccountEntity(Long number, String cbu, AccountType type){
        this.number = number;
        this.cbu = cbu;
        this.type = type;
    }

    public Long getNumber() {
        return number;
    }

    public String getCbu() {
        return cbu;
    }

    public AccountType getType() {
        return type;
    }

}