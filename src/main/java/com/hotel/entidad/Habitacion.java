package com.hotel.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "habitacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_habitacion", nullable = false, unique = true)
    private Integer numeroHabitacion;

    @Column(name = "piso", nullable = false)
    private Integer piso;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_habitacion", nullable = false, length = 20)
    private TipoHabitacion tipoHabitacion;

    @Column(name = "valor_noche", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorNoche;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoHabitacion estado;
}
