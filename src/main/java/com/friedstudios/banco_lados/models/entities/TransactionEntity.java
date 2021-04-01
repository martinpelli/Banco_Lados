package com.friedstudios.banco_lados.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String date;
    private BigDecimal amount;
    private String currency;
    private String origin;
    private String destination;

    public TransactionEntity(){
    }

    public TransactionEntity(BigInteger id, String date, BigDecimal amount, String currency, String origin, String destination) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.origin = origin;
        this.destination = destination;
    }

    public BigInteger getId() {
        return id;
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

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }
}
