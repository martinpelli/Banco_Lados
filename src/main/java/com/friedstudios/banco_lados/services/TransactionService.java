package com.friedstudios.banco_lados.services;

import com.friedstudios.banco_lados.models.dto.NewTransactionDTO;
import com.friedstudios.banco_lados.models.dto.TransactionDTO;
import com.friedstudios.banco_lados.models.dto.TransactionsDTO;
import com.friedstudios.banco_lados.models.entities.AccountEntity;
import com.friedstudios.banco_lados.models.entities.TransactionEntity;
import com.friedstudios.banco_lados.models.entities.UserEntity;
import com.friedstudios.banco_lados.models.mappers.TransactionMapper;
import com.friedstudios.banco_lados.models.repositories.AccountsRepositories;
import com.friedstudios.banco_lados.models.repositories.TransactionRepositories;
import com.friedstudios.banco_lados.models.repositories.UserRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepositories transactionRepositories;
    private final AccountsRepositories accountsRepositories;
    private final UserRepositories userRepositories;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepositories transactionRepositories, AccountsRepositories accountsRepositories, UserRepositories userRepositories, TransactionMapper transactionMapper) {
        this.transactionRepositories = transactionRepositories;
        this.accountsRepositories = accountsRepositories;
        this.userRepositories = userRepositories;
        this.transactionMapper = transactionMapper;
    }

    public TransactionsDTO getTransactions(Long accountNumber){
        List<TransactionEntity> transactionsEntities = transactionRepositories.findAllByOriginOrDestination(accountNumber.toString(),accountNumber.toString());
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        for (TransactionEntity transactionsEntity: transactionsEntities) {


            AccountEntity toAccountEntity = accountsRepositories.findByNumber(Long.parseLong(transactionsEntity.getDestination()));
            UserEntity toUserEntity = userRepositories.findByDni(toAccountEntity.getUserId());
            String transactionType;

            if(transactionsEntity.getOrigin().equals(accountNumber.toString())){
                transactionType = "GASTO";
            }
            else{
                transactionType = "INGRESO";
            }

            transactionDTOList.add(
                    new TransactionDTO(transactionsEntity.getDate().toString(),
                            transactionsEntity.getAmount(),
                            transactionsEntity.getCurrency().toString(),
                            transactionsEntity.getOrigin(),
                            toUserEntity.fullName() + "/CBU: "+ toAccountEntity.getCbu(),
                            transactionsEntity.getDescription(),
                            transactionType)
            );
        }
        BigDecimal balance = BigDecimal.ZERO;
        for (TransactionDTO transactionDTO : transactionDTOList){
            if (transactionDTO.getType().equals("Gasto")){
                balance = balance.subtract(transactionDTO.getAmount());
            }else{
                balance = balance.add(transactionDTO.getAmount());
            }
        }
        return new TransactionsDTO(transactionDTOList,balance);

        }

    public ResponseEntity createTransaction(NewTransactionDTO newTransactionDTO){
        try{
            TransactionEntity transactionEntity = transactionMapper.mapTransactionDTOtoTransactionEntity(newTransactionDTO);
            transactionRepositories.save(transactionEntity);
            return new ResponseEntity<>("Transacción Creada", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Algo salió mal",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    }



