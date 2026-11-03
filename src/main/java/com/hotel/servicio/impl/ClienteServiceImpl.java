package com.hotel.servicio.impl;

import com.hotel.dto.request.ClienteRequest;
import com.hotel.dto.response.ClienteResponse;
import com.hotel.entidad.Cliente;
import com.hotel.excepcion.BusinessException;
import com.hotel.excepcion.ResourceNotFoundException;
import com.hotel.repositorio.ClienteRepository;
import com.hotel.servicio.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponse crearCliente(ClienteRequest request) {
        if (clienteRepository.findByIdentificacion(request.getIdentificacion()).isPresent()) {
            throw new BusinessException("Ya existe un cliente con la identificación " + request.getIdentificacion());
        }

        Cliente cliente = Cliente.builder()
                .identificacion(request.getIdentificacion())
                .nombreCompleto(request.getNombreCompleto())
                .correoElectronico(request.getCorreoElectronico())
                .telefono(request.getTelefono())
                .build();

        Cliente guardado = clienteRepository.save(cliente);
        return ClienteResponse.fromEntity(guardado);
    }

    @Override
    public ClienteResponse obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));
        return ClienteResponse.fromEntity(cliente);
    }

    @Override
    public ClienteResponse obtenerClientePorIdentificacion(String identificacion) {
        Cliente cliente = clienteRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con identificación " + identificacion));
        return ClienteResponse.fromEntity(cliente);
    }

    @Override
    public List<ClienteResponse> listarClientes() {
        return clienteRepository.findAllByOrderByIdAsc().stream()
                .map(ClienteResponse::fromEntity)
                .toList();
    }
}
