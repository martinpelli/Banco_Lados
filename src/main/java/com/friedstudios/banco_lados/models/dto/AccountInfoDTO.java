package com.friedstudios.banco_lados.models.dto;

public class AccountInfoDTO {

    private String firstname;
    private String lastname;
    private String cbu;

    public AccountInfoDTO(){}

    public AccountInfoDTO(String firstname, String lastname, String cbu) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.cbu = cbu;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCbu() {
        return cbu;
    }
}
