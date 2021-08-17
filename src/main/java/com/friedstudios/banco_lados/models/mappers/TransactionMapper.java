package com.friedstudios.banco_lados.models.mappers;

import com.friedstudios.banco_lados.models.dto.NewTransactionDTO;
import com.friedstudios.banco_lados.models.entities.TransactionEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionMapper {
    public TransactionEntity mapTransactionDTOtoTransactionEntity(NewTransactionDTO newTransactionDTO){
        return new TransactionEntity(new Date(),
                newTransactionDTO.getAmount(),
                newTransactionDTO.getCurrency(),
                newTransactionDTO.getFrom(),
                newTransactionDTO.getTo(),
                newTransactionDTO.getDescription());


    }
}
