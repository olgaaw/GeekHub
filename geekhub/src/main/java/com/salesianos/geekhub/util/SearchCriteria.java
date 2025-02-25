package com.salesianos.geekhub.util;

public record SearchCriteria(
        String key,
        String operation,
        Object value
) {
}