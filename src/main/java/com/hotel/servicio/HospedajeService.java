package com.hotel.servicio;

import com.hotel.dto.request.CheckInRequest;
import com.hotel.dto.request.CheckOutRequest;
import com.hotel.dto.response.FacturaResponse;
import com.hotel.dto.response.HospedajeResponse;

import java.util.List;

public interface HospedajeService {

    HospedajeResponse registrarCheckIn(CheckInRequest request);

    FacturaResponse registrarCheckOut(CheckOutRequest request);

    List<HospedajeResponse> obtenerHospedajesActivos();

    HospedajeResponse obtenerHospedajeActivoPorCliente(Long clienteId);

    List<HospedajeResponse> obtenerHistorialPorCliente(Long clienteId);
}
