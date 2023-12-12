package com.TP.TP.mappers;

import com.TP.TP.models.Account;
import com.TP.TP.models.dtos.AccountDTO;
import com.TP.TP.services.UserService;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;

@UtilityClass
public class AccountMapper {

    @Autowired
    private UserService userService;


    public static AccountDTO accountToDto(Account account){
        AccountDTO dto = new AccountDTO();
        dto.setAlias(account.getAlias());
        dto.setCbu(account.getCbu());
        dto.setType(account.getType());
        dto.setAmount(account.getAmount());
        dto.setId(account.getId());
        dto.setIdOwner(account.getIdOwner());
        return dto;
    }

    public static Account dtoToAccount(AccountDTO dto){
        Account account = new Account();
        account.setAlias(dto.getAlias());
        account.setType(dto.getType());
        account.setCbu(dto.getCbu());
        account.setAmount(dto.getAmount());
        account.setIdOwner(dto.getIdOwner());
        return account;
    }
}
