package com.friedstudios.banco_lados.models.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Dictionary;

@Entity(name = "users")
public class UserEntity {
    @Id
    private String dni;
    private String firstname;
    private String lastName;
    private String adress;

    public UserEntity(){
    }

    public UserEntity(String dni, String firstname, String lastName, String adress) {
        this.dni = dni;
        this.firstname = firstname;
        this.lastName = lastName;
        this.adress = adress;
    }

    public String getDni() {
        return dni;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAdress() {
        return adress;
    }

    public String fullName(){
        return firstname + " "+ lastName;
    }

}
