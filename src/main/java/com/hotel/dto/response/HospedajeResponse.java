package com.hotel.dto.response;

import com.hotel.entidad.Hospedaje;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospedajeResponse {

    private Long id;
    private Long clienteId;
    private String nombreCliente;
    private Long habitacionId;
    private Integer numeroHabitacion;
    private String tipoHabitacion;
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraSalida;
    private Boolean activo;
    private LocalDateTime fechaCreacion;

    public static HospedajeResponse fromEntity(Hospedaje hospedaje) {
        return HospedajeResponse.builder()
                .id(hospedaje.getId())
                .clienteId(hospedaje.getCliente().getId())
                .nombreCliente(hospedaje.getCliente().getNombreCompleto())
                .habitacionId(hospedaje.getHabitacion().getId())
                .numeroHabitacion(hospedaje.getHabitacion().getNumeroHabitacion())
                .tipoHabitacion(hospedaje.getHabitacion().getTipoHabitacion().name())
                .fechaHoraIngreso(hospedaje.getFechaHoraIngreso())
                .fechaHoraSalida(hospedaje.getFechaHoraSalida())
                .activo(hospedaje.getActivo())
                .fechaCreacion(hospedaje.getFechaCreacion())
                .build();
    }
}
