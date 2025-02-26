package com.salesianos.geekhub.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PaginationDto<T>  (
        int numPagina,
        int tamanioPagina,
        long elementosEncontrados,
        int paginasTotales,
        List<T> contenido

) {
    public static <T> PaginationDto<T> of(Page<T> page) {
        return new PaginationDto<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent()
        );
    }

}