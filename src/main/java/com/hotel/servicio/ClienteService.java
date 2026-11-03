package com.hotel.servicio;

import com.hotel.dto.request.ClienteRequest;
import com.hotel.dto.response.ClienteResponse;

import java.util.List;

public interface ClienteService {

    ClienteResponse crearCliente(ClienteRequest request);

    ClienteResponse obtenerClientePorId(Long id);

    ClienteResponse obtenerClientePorIdentificacion(String identificacion);

    List<ClienteResponse> listarClientes();
}
