package com.friedstudios.banco_lados.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Date;

@Entity(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private Date date;
    private BigDecimal amount;
    private String currency;
    private String origin;
    private String destination;
    private String description;

    public TransactionEntity(){
    }

    public TransactionEntity(Date date, String description, BigDecimal amount, String currency, String from, String to){
    }

    public TransactionEntity(Date date, BigDecimal amount, String currency, String origin, String destination, String description) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.origin = origin;
        this.destination = destination;
        this.description = description;
    }

    public BigInteger getId() {
        return id;
    }

    public Date getDate() {
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

    public String getDescription() {
        return description;
    }
}
