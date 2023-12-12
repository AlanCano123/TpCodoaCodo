package com.TP.TP.mappers;

import com.TP.TP.models.Prestamo;
import com.TP.TP.models.dtos.PrestamoDTO;
import com.TP.TP.repositories.UserRepository;
import com.TP.TP.services.UserService;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;

@UtilityClass
public class PrestamoMapper {

    @Autowired
    private UserService userService;

    public static PrestamoDTO prestamoToDTO(Prestamo prestamo){
        PrestamoDTO dto = new PrestamoDTO();
        dto.setId(prestamo.getId());
        dto.setDescripcion(prestamo.getDescripcion());
        dto.setMonto(prestamo.getMonto());
        dto.setVencimiento(prestamo.getVencimiento());
        dto.setIdAccount(prestamo.getIdAccount());
        return dto;
    }

    public static Prestamo dtoToPrestamo(PrestamoDTO dto){
        Prestamo prestamo = new Prestamo();
        prestamo.setDescripcion(dto.getDescripcion());
        prestamo.setMonto(dto.getMonto());
        prestamo.setVencimiento(dto.getVencimiento());
        prestamo.setIdAccount(dto.getIdAccount());
        return prestamo;
    }
}
