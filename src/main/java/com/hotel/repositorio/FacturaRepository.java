package com.hotel.repositorio;

import com.hotel.entidad.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    List<Factura> findAllByOrderByFechaGeneracionDesc();

    Optional<Factura> findByHospedajeId(Long hospedajeId);
}
