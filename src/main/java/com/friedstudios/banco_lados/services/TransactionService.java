package com.friedstudios.banco_lados.services;

import com.friedstudios.banco_lados.exceptions.NotFoundException;
import com.friedstudios.banco_lados.exceptions.BadRequestException;
import com.friedstudios.banco_lados.models.dto.*;
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
import java.math.BigInteger;
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

    public ResponseEntity createTransaction(NewTransactionDTO newTransactionDTO) {

        if (newTransactionDTO.getFrom().equals(newTransactionDTO.getTo())) {
            throw new BadRequestException("Error al crear la transacción. El origen y el destino de la transacción no puede ser el mismo.");
        }
        AccountEntity accountEntity = accountsRepositories.findByNumber(Long.parseLong(newTransactionDTO.getTo()));
        if (accountEntity == null){
            throw new BadRequestException("Error al crear la transacción. La cuenta de destino no existe");
        }
        if(newTransactionDTO.getAmount().intValue() <= 0){
            throw new BadRequestException("Error al crear la transacción. Error en el monto.");
        }
        try {
                 TransactionEntity transactionEntity = transactionMapper.mapTransactionDTOtoTransactionEntity(newTransactionDTO);
                transactionRepositories.save(transactionEntity);
                return new ResponseEntity<>("Transacción Creada", HttpStatus.OK);

        } catch (Exception e) {
                return new ResponseEntity<>("Algo salió mal", HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }

    public TransactionDetailedDTO getTransactionId(BigInteger id){
        TransactionEntity transactionEntity = transactionRepositories.findById(id);
        if (transactionEntity != null) {
            AccountEntity toAccountEntity = accountsRepositories.findByNumber(Long.parseLong(transactionEntity.getDestination()));
            AccountEntity fromAccountEntity = accountsRepositories.findByNumber(Long.parseLong(transactionEntity.getOrigin()));
            UserEntity toUserEntity = userRepositories.findByDni(toAccountEntity.getUserId());
            UserEntity fromUserEntity = userRepositories.findByDni(fromAccountEntity.getUserId());
            AccountInfoDTO accountInfoDTOfrom = new AccountInfoDTO(
                    fromUserEntity.getFirstname(),
                    fromUserEntity.getLastName(),
                    fromAccountEntity.getCbu());
            AccountInfoDTO accountInfoDTOto = new AccountInfoDTO(
                    toUserEntity.getFirstname(),
                    toUserEntity.getLastName(),
                    toAccountEntity.getCbu());
            TransactionDetailedDTO transactionDetailedDTO = new TransactionDetailedDTO(
                    transactionEntity.getDate().toString(),
                    transactionEntity.getAmount(),
                    transactionEntity.getCurrency().toString(),
                    transactionEntity.getDescription(),
                    accountInfoDTOfrom,
                    accountInfoDTOto);
            return transactionDetailedDTO;
        }else{
            throw new NotFoundException("No existe la transacción");
        }

    }
    }



