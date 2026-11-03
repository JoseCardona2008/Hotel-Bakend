package com.hotel.servicio.impl;

import com.hotel.dto.request.CheckInRequest;
import com.hotel.dto.request.CheckOutRequest;
import com.hotel.dto.response.FacturaResponse;
import com.hotel.dto.response.HospedajeResponse;
import com.hotel.entidad.Cliente;
import com.hotel.entidad.EstadoHabitacion;
import com.hotel.entidad.Factura;
import com.hotel.entidad.Habitacion;
import com.hotel.entidad.Hospedaje;
import com.hotel.excepcion.BusinessException;
import com.hotel.excepcion.ResourceNotFoundException;
import com.hotel.repositorio.ClienteRepository;
import com.hotel.repositorio.FacturaRepository;
import com.hotel.repositorio.HospedajeRepository;
import com.hotel.servicio.HabitacionService;
import com.hotel.servicio.HospedajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospedajeServiceImpl implements HospedajeService {

    private static final LocalTime HORA_CHECK_IN = LocalTime.of(16, 0);
    private static final LocalTime HORA_CHECK_OUT = LocalTime.of(13, 0);

    private final HospedajeRepository hospedajeRepository;
    private final ClienteRepository clienteRepository;
    private final FacturaRepository facturaRepository;
    private final HabitacionService habitacionService;

    @Override
    @Transactional
    public HospedajeResponse registrarCheckIn(CheckInRequest request) {
        validarHorarioCheckIn();

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + request.getClienteId()));

        if (hospedajeRepository.findByClienteIdAndActivoTrue(request.getClienteId()).isPresent()) {
            throw new BusinessException("El cliente ya tiene un hospedaje activo");
        }

        Habitacion habitacion = habitacionService.obtenerHabitacionDisponiblePorTipo(request.getTipoHabitacion());

        Hospedaje hospedaje = Hospedaje.builder()
                .cliente(cliente)
                .habitacion(habitacion)
                .fechaHoraIngreso(LocalDateTime.now())
                .activo(Boolean.TRUE)
                .build();

        Hospedaje guardado = hospedajeRepository.save(hospedaje);
        habitacion.setEstado(EstadoHabitacion.OCUPADA);
        habitacionService.actualizarEstadoHabitacion(habitacion.getId(), EstadoHabitacion.OCUPADA);

        return HospedajeResponse.fromEntity(guardado);
    }

    @Override
    @Transactional
    public FacturaResponse registrarCheckOut(CheckOutRequest request) {
        validarHorarioCheckOut();

        Hospedaje hospedaje = hospedajeRepository.findByClienteIdAndActivoTrue(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe un hospedaje activo para el cliente con id " + request.getClienteId()));

        LocalDateTime fechaSalida = LocalDateTime.now();
        hospedaje.setFechaHoraSalida(fechaSalida);
        hospedaje.setActivo(Boolean.FALSE);
        hospedajeRepository.save(hospedaje);

        Habitacion habitacion = hospedaje.getHabitacion();
        habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
        habitacionService.actualizarEstadoHabitacion(habitacion.getId(), EstadoHabitacion.DISPONIBLE);

        int cantidadNoches = Math.max(1, (int) ChronoUnit.DAYS.between(hospedaje.getFechaHoraIngreso().toLocalDate(), fechaSalida.toLocalDate()));
        BigDecimal valorNoche = habitacion.getValorNoche();
        BigDecimal valorTotal = valorNoche.multiply(BigDecimal.valueOf(cantidadNoches));

        Factura factura = facturaRepository.findByHospedajeId(hospedaje.getId())
                .orElseGet(Factura::new);

        factura.asignarDatos(hospedaje, cantidadNoches, valorNoche, valorTotal);
        Factura guardada = facturaRepository.save(factura);

        return FacturaResponse.fromEntity(guardada);
    }

    @Override
    public List<HospedajeResponse> obtenerHospedajesActivos() {
        return hospedajeRepository.findByActivoTrueOrderByFechaHoraIngresoDesc().stream()
                .map(HospedajeResponse::fromEntity)
                .toList();
    }

    @Override
    public HospedajeResponse obtenerHospedajeActivoPorCliente(Long clienteId) {
        Hospedaje hospedaje = hospedajeRepository.findByClienteIdAndActivoTrue(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente no tiene un hospedaje activo"));
        return HospedajeResponse.fromEntity(hospedaje);
    }

    @Override
    public List<HospedajeResponse> obtenerHistorialPorCliente(Long clienteId) {
        return hospedajeRepository.findByClienteIdOrderByFechaHoraIngresoDesc(clienteId).stream()
                .map(HospedajeResponse::fromEntity)
                .toList();
    }

    private void validarHorarioCheckIn() {
        if (LocalTime.now().isBefore(HORA_CHECK_IN)) {
            throw new BusinessException("El check-in oficial inicia a las 16:00 horas.");
        }
    }

    private void validarHorarioCheckOut() {
        if (LocalTime.now().isBefore(HORA_CHECK_OUT)) {
            throw new BusinessException("El check-out oficial inicia a las 13:00 horas.");
        }
    }
}
