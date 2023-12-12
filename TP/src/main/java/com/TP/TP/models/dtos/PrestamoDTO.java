package com.TP.TP.models.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestamoDTO {

    private Long id;
    private String descripcion;
    private double monto;
    private LocalDate vencimiento;
    private Long idAccount;
}
