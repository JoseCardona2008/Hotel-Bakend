package com.hotel.servicio;

import com.hotel.dto.response.HabitacionResponse;
import com.hotel.entidad.EstadoHabitacion;
import com.hotel.entidad.Habitacion;
import com.hotel.entidad.TipoHabitacion;

import java.util.List;

public interface HabitacionService {

    List<HabitacionResponse> obtenerTodas();

    List<HabitacionResponse> obtenerDisponiblesPorTipo(TipoHabitacion tipoHabitacion);

    Habitacion obtenerHabitacionPorId(Long id);

    Habitacion obtenerHabitacionDisponiblePorTipo(TipoHabitacion tipoHabitacion);

    Habitacion obtenerHabitacionPorNumero(Integer numeroHabitacion);

    void actualizarEstadoHabitacion(Long habitacionId, EstadoHabitacion estadoHabitacion);
}
