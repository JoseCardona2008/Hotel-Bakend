package com.hotel.dto.response;

import com.hotel.entidad.Factura;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaResponse {

    private Long id;
    private Long hospedajeId;
    private LocalDateTime fechaGeneracion;
    private Integer cantidadNoches;
    private BigDecimal valorNoche;
    private BigDecimal valorTotal;

    public static FacturaResponse fromEntity(Factura factura) {
        return FacturaResponse.builder()
                .id(factura.getId())
                .hospedajeId(factura.getHospedaje().getId())
                .fechaGeneracion(factura.getFechaGeneracion())
                .cantidadNoches(factura.getCantidadNoches())
                .valorNoche(factura.getValorNoche())
                .valorTotal(factura.getValorTotal())
                .build();
    }
}
