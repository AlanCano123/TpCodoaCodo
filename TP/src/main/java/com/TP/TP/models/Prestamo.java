package com.TP.TP.models;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import com.TP.TP.models.User;
import java.time.LocalDate;


@Entity
@Table(name = "prestamos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "monto")
    private double monto;

    @Column(name = "vencimiento")
    private LocalDate vencimiento;

   @OneToOne
   private User user;

}
