package com.TP.TP.models.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferDTO {

    private Long id;

    private Long origin;

    private Long target;

    private Date date;

    private BigDecimal amount;
}
