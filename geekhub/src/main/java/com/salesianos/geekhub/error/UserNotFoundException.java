package com.salesianos.geekhub.error;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(UUID id) {
        super("No hay usuario con ese ID: %d".formatted(id));
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException() {
        super("No hay usuario con esos requisitos de búsqueda");
    }
}
