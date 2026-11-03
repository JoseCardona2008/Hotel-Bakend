package com.hotel.controlador;

import com.hotel.dto.request.ClienteRequest;
import com.hotel.dto.response.ClienteResponse;
import com.hotel.respuesta.ApiResponse;
import com.hotel.servicio.ClienteService;
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
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteResponse>> crearCliente(@Valid @RequestBody ClienteRequest request) {
        ClienteResponse response = clienteService.crearCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Cliente creado exitosamente", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClienteResponse>>> listarClientes() {
        return ResponseEntity.ok(ApiResponse.success("Lista de clientes", clienteService.listarClientes()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Cliente encontrado", clienteService.obtenerClientePorId(id)));
    }

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<ApiResponse<ClienteResponse>> obtenerClientePorIdentificacion(@PathVariable String identificacion) {
        return ResponseEntity.ok(ApiResponse.success("Cliente encontrado", clienteService.obtenerClientePorIdentificacion(identificacion)));
    }
}
