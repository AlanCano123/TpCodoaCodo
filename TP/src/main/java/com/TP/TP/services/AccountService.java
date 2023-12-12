package com.TP.TP.services;

import com.TP.TP.exceptions.AccountNotFoundException;
import com.TP.TP.exceptions.UserNotExistsException;
import com.TP.TP.mappers.AccountMapper;
import com.TP.TP.models.Account;
import com.TP.TP.models.User;
import com.TP.TP.models.dtos.AccountDTO;
import com.TP.TP.models.enums.AccountType;
import com.TP.TP.repositories.AccountRepository;
import com.TP.TP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<AccountDTO> getAccounts() {
        List<Account> accounts = repository.findAll();
        return accounts.stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }

    public AccountDTO createAccount(AccountDTO dto) {
        Optional<User> user = userRepository.findById(dto.getIdOwner());
        if (user.isEmpty()){
            throw new UserNotExistsException("El usuario no existe.");
        }
        dto.setType(AccountType.SAVINGS_BANK);
        dto.setAmount(BigDecimal.ZERO);
        Account newAccount = repository.save(AccountMapper.dtoToAccount(dto));
        return AccountMapper.accountToDto(newAccount);
    }

    public AccountDTO getAccountById(Long id) {
        Account entity = repository.findById(id).get();
        return AccountMapper.accountToDto(entity);
    }
    public String deleteAccount(Long id) throws Exception {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "La cuenta con id: " + id + " ha sido eliminada";
        } else {
            throw new AccountNotFoundException("La cuenta a eliminar no existe");
        }
    }

    public AccountDTO updateAccount(Long id, AccountDTO dto) {
        if (repository.existsById(id)) {
            Account accountToModify = repository.findById(id).get();

            if (dto.getAlias() != null) {
                accountToModify.setAlias(dto.getAlias());
            }

            if (dto.getType() != null) {
                accountToModify.setType(dto.getType());
            }

            if (dto.getCbu() != null) {
                accountToModify.setCbu(dto.getCbu());
            }

            if (dto.getAmount() != null) {
                accountToModify.setAmount(dto.getAmount());
            }

            Account accountModified = repository.save(accountToModify);

            return AccountMapper.accountToDto(accountModified);
        }
        return null;
    }
}
