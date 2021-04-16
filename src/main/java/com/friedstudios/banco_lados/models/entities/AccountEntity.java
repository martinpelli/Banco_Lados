package com.friedstudios.banco_lados.models.entities;

import com.friedstudios.banco_lados.models.enums.AccountType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity(name = "accounts")
public class AccountEntity {
    @Id
    private Long number;
    private String cbu;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private String userId;

    public AccountEntity() {

    }


    public AccountEntity(Long number, String cbu, AccountType type){
    }

    public AccountEntity(Long number, String cbu, AccountType type,String userId){
        this.number = number;
        this.cbu = cbu;
        this.type = type;
        this.userId = userId;
    }

    public Long getNumber() {
        return number;
    }

    public String getUserId() {
        return userId;
    }

    public String getCbu() {
        return cbu;
    }

    public AccountType getType() {
        return type;
    }

}