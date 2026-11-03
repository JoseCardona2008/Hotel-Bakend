package com.hotel.repositorio;

import com.hotel.entidad.Hospedaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospedajeRepository extends JpaRepository<Hospedaje, Long> {

    Optional<Hospedaje> findByClienteIdAndActivoTrue(Long clienteId);

    List<Hospedaje> findByActivoTrueOrderByFechaHoraIngresoDesc();

    List<Hospedaje> findAllByOrderByFechaHoraIngresoDesc();

    List<Hospedaje> findByClienteIdOrderByFechaHoraIngresoDesc(Long clienteId);
}
