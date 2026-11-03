package com.hotel.controlador;

import com.hotel.dto.response.HabitacionResponse;
import com.hotel.entidad.TipoHabitacion;
import com.hotel.respuesta.ApiResponse;
import com.hotel.servicio.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<HabitacionResponse>>> listarHabitaciones() {
        return ResponseEntity.ok(ApiResponse.success("Habitaciones del hotel", habitacionService.obtenerTodas()));
    }

    @GetMapping("/disponibles/{tipoHabitacion}")
    public ResponseEntity<ApiResponse<List<HabitacionResponse>>> listarHabitacionesDisponibles(@PathVariable TipoHabitacion tipoHabitacion) {
        return ResponseEntity.ok(ApiResponse.success(
                "Habitaciones disponibles del tipo " + tipoHabitacion,
                habitacionService.obtenerDisponiblesPorTipo(tipoHabitacion)));
    }
}
