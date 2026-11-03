package com.hotel.dto.response;

import com.hotel.entidad.Cliente;
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
public class ClienteResponse {

    private Long id;
    private String identificacion;
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono;
    private LocalDateTime fechaCreacion;

    public static ClienteResponse fromEntity(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .identificacion(cliente.getIdentificacion())
                .nombreCompleto(cliente.getNombreCompleto())
                .correoElectronico(cliente.getCorreoElectronico())
                .telefono(cliente.getTelefono())
                .fechaCreacion(cliente.getFechaCreacion())
                .build();
    }
}
