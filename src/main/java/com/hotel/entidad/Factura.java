package com.hotel.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "factura")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hospedaje_id", nullable = false, unique = true, updatable = false)
    @Setter(AccessLevel.NONE)
    private Hospedaje hospedaje;

    @Column(name = "fecha_generacion", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime fechaGeneracion;

    @Column(name = "cantidad_noches", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Integer cantidadNoches;

    @Column(name = "valor_noche", nullable = false, precision = 12, scale = 2, updatable = false)
    @Setter(AccessLevel.NONE)
    private BigDecimal valorNoche;

    @Column(name = "valor_total", nullable = false, precision = 12, scale = 2, updatable = false)
    @Setter(AccessLevel.NONE)
    private BigDecimal valorTotal;

    @PrePersist
    protected void onCreate() {
        if (this.fechaGeneracion == null) {
            this.fechaGeneracion = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        throw new UnsupportedOperationException("La factura no puede ser modificada después de generarse.");
    }

    @PreRemove
    protected void onRemove() {
        throw new UnsupportedOperationException("La factura no puede ser eliminada.");
    }

    public void asignarDatos(Hospedaje hospedaje, Integer cantidadNoches, BigDecimal valorNoche, BigDecimal valorTotal) {
        this.hospedaje = hospedaje;
        this.cantidadNoches = cantidadNoches;
        this.valorNoche = valorNoche;
        this.valorTotal = valorTotal;
    }
}
