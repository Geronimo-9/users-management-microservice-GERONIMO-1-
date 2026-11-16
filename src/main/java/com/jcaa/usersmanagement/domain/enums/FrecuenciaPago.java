package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.FormatoInvalidoExcepcion;

public enum FrecuenciaPago {
    SEMANAL,
    QUINCENAL,
    MENSUAL,
    SEMESTRAL,
    ANUAL;

    public static FrecuenciaPago fromString(final String value) {
        if (value == null || value.isBlank()) {
            throw FormatoInvalidoExcepcion.valorNoPermitido("frecuenciaPago", String.valueOf(value));
        }
        for (final FrecuenciaPago frecuencia : values()) {
            if (frecuencia.name().equalsIgnoreCase(value.trim())) {
                return frecuencia;
            }
        }
        throw FormatoInvalidoExcepcion.valorNoPermitido("frecuenciaPago", value);
    }
}