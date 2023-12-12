package com.TP.TP.services;
import com.TP.TP.exceptions.AccountNotFoundException;
import com.TP.TP.mappers.PrestamoMapper;
import com.TP.TP.models.Account;
import com.TP.TP.models.dtos.PrestamoDTO;
import com.TP.TP.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TP.TP.repositories.UserRepository;
import com.TP.TP.exceptions.UserNotExistsException;
import  com.TP.TP.models.Prestamo;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.TP.TP.repositories.PrestamoRepository;

@Service
public class PrestamoService {

    private final AccountRepository accountRepository;
    @Autowired
    private final PrestamoRepository prestamoRepository;

    public PrestamoService(AccountRepository accountRepository, PrestamoRepository prestamoRepository) {
        this.accountRepository = accountRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public PrestamoDTO createPrestamo(PrestamoDTO dto,int dias){
    Optional<Account> account = accountRepository.findById(dto.getIdAccount());
    if(account.isEmpty()){
        throw new AccountNotFoundException("Esta cuenta no existe");
    }
    dto.setMonto(calculoIntereses(dto.getMonto(), dias));
    dto.setVencimiento(LocalDate.now().plusDays(dias));
    Prestamo nuevoPrestamo = PrestamoMapper.dtoToPrestamo(dto);
    account.get().setIdPrestamo(nuevoPrestamo.getId());
    nuevoPrestamo.setIdAccount(account.get().getId());
    accountRepository.save(account.get());
    prestamoRepository.save(nuevoPrestamo);
    return PrestamoMapper.prestamoToDTO(nuevoPrestamo);
    }

    public double calculoIntereses(double monto,int dias){
        double montoFinal;
        double calculo;
        if(dias<30){
            calculo = (monto * dias * 0.020);
        }else{
            calculo = (monto * dias * 0.030);
        }
        montoFinal=monto + calculo;
        return montoFinal;
    }

    public List<PrestamoDTO> getPrestamos(){
        List<Prestamo> prestamos = prestamoRepository.findAll();
        return prestamos.stream()
                .map(PrestamoMapper::prestamoToDTO)
                .collect(Collectors.toList());
    }

}
