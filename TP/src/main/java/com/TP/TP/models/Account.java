package com.TP.TP.models;

import com.TP.TP.models.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;
import com.TP.TP.models.User;

import java.math.BigDecimal;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_cuenta")
    private AccountType type;

    private String cbu;

    private String alias;

    @Column(name = "monto")
    private BigDecimal amount;

    private Long idOwner;

    private Long idPrestamo;
}
