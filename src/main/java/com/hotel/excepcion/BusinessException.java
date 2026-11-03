package com.hotel.excepcion;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
