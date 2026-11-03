package com.hotel.dto.response;

import com.hotel.entidad.EstadoHabitacion;
import com.hotel.entidad.Habitacion;
import com.hotel.entidad.TipoHabitacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitacionResponse {

    private Long id;
    private Integer numeroHabitacion;
    private Integer piso;
    private TipoHabitacion tipoHabitacion;
    private BigDecimal valorNoche;
    private EstadoHabitacion estado;

    public static HabitacionResponse fromEntity(Habitacion habitacion) {
        return HabitacionResponse.builder()
                .id(habitacion.getId())
                .numeroHabitacion(habitacion.getNumeroHabitacion())
                .piso(habitacion.getPiso())
                .tipoHabitacion(habitacion.getTipoHabitacion())
                .valorNoche(habitacion.getValorNoche())
                .estado(habitacion.getEstado())
                .build();
    }
}
