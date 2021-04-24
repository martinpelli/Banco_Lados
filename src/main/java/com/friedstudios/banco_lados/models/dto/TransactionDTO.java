package com.friedstudios.banco_lados.models.dto;

import java.math.BigDecimal;

public class TransactionDTO {

    private String description;
    private String date;
    private BigDecimal amount;
    private String currency;
    private String from;
    private String to;
    private String type;

    public TransactionDTO() {
    }


    public TransactionDTO(String date, BigDecimal amount, String currency, String from, String to, String description, String type) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.from = from;
        this.to = to;
        this.type = type;
    }


    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getType() {return type; }

}
