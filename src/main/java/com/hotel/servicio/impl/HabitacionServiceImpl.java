package com.hotel.servicio.impl;

import com.hotel.dto.response.HabitacionResponse;
import com.hotel.entidad.EstadoHabitacion;
import com.hotel.entidad.Habitacion;
import com.hotel.entidad.TipoHabitacion;
import com.hotel.excepcion.BusinessException;
import com.hotel.excepcion.ResourceNotFoundException;
import com.hotel.repositorio.HabitacionRepository;
import com.hotel.servicio.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitacionServiceImpl implements HabitacionService {

    private final HabitacionRepository habitacionRepository;

    @Override
    public List<HabitacionResponse> obtenerTodas() {
        return habitacionRepository.findAllByOrderByNumeroHabitacionAsc().stream()
                .map(HabitacionResponse::fromEntity)
                .toList();
    }

    @Override
    public List<HabitacionResponse> obtenerDisponiblesPorTipo(TipoHabitacion tipoHabitacion) {
        return habitacionRepository.findByEstadoAndTipoHabitacionOrderByNumeroHabitacionAsc(
                        EstadoHabitacion.DISPONIBLE, tipoHabitacion)
                .stream()
                .map(HabitacionResponse::fromEntity)
                .toList();
    }

    @Override
    public Habitacion obtenerHabitacionPorId(Long id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habitación no encontrada con id " + id));
    }

    @Override
    public Habitacion obtenerHabitacionDisponiblePorTipo(TipoHabitacion tipoHabitacion) {
        return habitacionRepository.findByEstadoAndTipoHabitacionOrderByNumeroHabitacionAsc(
                        EstadoHabitacion.DISPONIBLE, tipoHabitacion)
                .stream()
                .findFirst()
                .orElseThrow(() -> new BusinessException("No hay habitaciones disponibles del tipo " + tipoHabitacion));
    }

    @Override
    public Habitacion obtenerHabitacionPorNumero(Integer numeroHabitacion) {
        return habitacionRepository.findByNumeroHabitacion(numeroHabitacion)
                .orElseThrow(() -> new ResourceNotFoundException("Habitación no encontrada con número " + numeroHabitacion));
    }

    @Override
    public void actualizarEstadoHabitacion(Long habitacionId, EstadoHabitacion estadoHabitacion) {
        Habitacion habitacion = obtenerHabitacionPorId(habitacionId);
        habitacion.setEstado(estadoHabitacion);
        habitacionRepository.save(habitacion);
    }
}
