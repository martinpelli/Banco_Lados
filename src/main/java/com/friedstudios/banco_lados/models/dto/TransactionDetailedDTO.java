package com.friedstudios.banco_lados.models.dto;

import java.math.BigDecimal;

public class TransactionDetailedDTO {

    private String description;
    private String date;
    private BigDecimal amount;
    private String currency;
    private AccountInfoDTO from;
    private AccountInfoDTO to;

    public TransactionDetailedDTO() {
    }


    public TransactionDetailedDTO(String date, BigDecimal amount, String currency, String description, AccountInfoDTO from, AccountInfoDTO to) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.from = from;
        this.to = to;
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

    public AccountInfoDTO getFrom() { return from; }

    public AccountInfoDTO getTo() { return to; }
}
