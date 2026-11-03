package com.hotel.controlador;

import com.hotel.dto.request.CheckInRequest;
import com.hotel.dto.request.CheckOutRequest;
import com.hotel.dto.response.FacturaResponse;
import com.hotel.dto.response.HospedajeResponse;
import com.hotel.respuesta.ApiResponse;
import com.hotel.servicio.HospedajeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hospedajes")
@RequiredArgsConstructor
public class HospedajeController {

    private final HospedajeService hospedajeService;

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<HospedajeResponse>> registrarCheckIn(@Valid @RequestBody CheckInRequest request) {
        HospedajeResponse response = hospedajeService.registrarCheckIn(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Check-in registrado correctamente", response));
    }

    @PostMapping("/check-out")
    public ResponseEntity<ApiResponse<FacturaResponse>> registrarCheckOut(@Valid @RequestBody CheckOutRequest request) {
        FacturaResponse response = hospedajeService.registrarCheckOut(request);
        return ResponseEntity.ok(ApiResponse.success("Check-out registrado correctamente", response));
    }

    @GetMapping("/activos")
    public ResponseEntity<ApiResponse<List<HospedajeResponse>>> listarHospedajesActivos() {
        return ResponseEntity.ok(ApiResponse.success("Hospedajes activos", hospedajeService.obtenerHospedajesActivos()));
    }

    @GetMapping("/cliente/{clienteId}/activo")
    public ResponseEntity<ApiResponse<HospedajeResponse>> obtenerHospedajeActivoPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(ApiResponse.success("Hospedaje activo del cliente", hospedajeService.obtenerHospedajeActivoPorCliente(clienteId)));
    }

    @GetMapping("/cliente/{clienteId}/historial")
    public ResponseEntity<ApiResponse<List<HospedajeResponse>>> obtenerHistorialCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(ApiResponse.success("Historial de hospedajes del cliente", hospedajeService.obtenerHistorialPorCliente(clienteId)));
    }
}
