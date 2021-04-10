package com.friedstudios.banco_lados.models.dto;

import java.math.BigDecimal;
import java.util.List;

public class TransactionsDTO {
    private List<TransactionDTO> transactions;
    private BigDecimal balance;

    public TransactionsDTO(){

    }

    public TransactionsDTO(List<TransactionDTO> transactions,BigDecimal balance) {
        this.transactions = transactions;
        this.balance = balance;

    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public int getTransactionsNumber(){return transactions.size();}

    public BigDecimal getBalance() { return balance; }
}
