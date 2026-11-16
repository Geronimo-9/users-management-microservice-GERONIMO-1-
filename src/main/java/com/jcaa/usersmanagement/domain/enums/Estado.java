package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.FormatoInvalidoExcepcion;

public enum Estado {
    VIGENTE,
    VENCIDO;

    public static Estado fromString(final String value) {
        if (value == null || value.isBlank()) {
            throw FormatoInvalidoExcepcion.valorNoPermitido("estado", String.valueOf(value));
        }
        for (final Estado estado : values()) {
            if (estado.name().equalsIgnoreCase(value.trim())) {
                return estado;
            }
        }
        throw FormatoInvalidoExcepcion.valorNoPermitido("estado", value);
    }
}