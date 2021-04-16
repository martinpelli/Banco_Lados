package com.friedstudios.banco_lados.controllers;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
