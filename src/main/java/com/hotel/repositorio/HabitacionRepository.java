package com.hotel.repositorio;

import com.hotel.entidad.EstadoHabitacion;
import com.hotel.entidad.Habitacion;
import com.hotel.entidad.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    Optional<Habitacion> findByNumeroHabitacion(Integer numeroHabitacion);

    List<Habitacion> findByEstadoAndTipoHabitacionOrderByNumeroHabitacionAsc(EstadoHabitacion estado, TipoHabitacion tipoHabitacion);

    List<Habitacion> findAllByOrderByNumeroHabitacionAsc();
}
