package com.TP.TP.models.dtos;

import com.TP.TP.models.enums.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private AccountType type;
    private String cbu;
    private String alias;
    private BigDecimal amount;
    private Long idOwner;
}
