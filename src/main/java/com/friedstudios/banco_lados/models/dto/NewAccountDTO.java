package com.friedstudios.banco_lados.models.dto;

import com.friedstudios.banco_lados.models.enums.AccountType;

public class NewAccountDTO {
    private Long number;
    private String cbu;
    private AccountType type;
    private String userId;

    public NewAccountDTO() {
    }

    public NewAccountDTO(Long number, String cbu, AccountType type, String userId) {
        this.number = number;
        this.cbu = cbu;
        this.type = type;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

}
