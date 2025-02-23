package com.salesianos.geekhub.error;

import java.util.UUID;

public class InterestNotFoundException extends RuntimeException{
    public InterestNotFoundException(UUID id) {
        super("No hay interés con ese ID: %s".formatted(id));
    }

    public InterestNotFoundException(String msg) {
        super(msg);
    }

    public InterestNotFoundException() {
        super("No hay interés con esos requisitos de búsqueda");
    }
}

