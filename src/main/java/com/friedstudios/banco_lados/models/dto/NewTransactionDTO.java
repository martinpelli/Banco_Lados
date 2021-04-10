package com.friedstudios.banco_lados.models.dto;

import java.math.BigDecimal;


public class NewTransactionDTO {
    private BigDecimal amount;
    private String currency;
    private String from;
    private String to;
    private String description;

    public NewTransactionDTO() {
    }

    public NewTransactionDTO(BigDecimal amount, String currency, String from, String to, String description) {
        this.amount = amount;
        this.currency = currency;
        this.from = from;
        this.to = to;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
