package com.jcaa.usersmanagement.domain.valueobject.contrato;

import com.jcaa.usersmanagement.domain.exception.FechaInvalidaExcepcion;
import com.jcaa.usersmanagement.domain.exception.ValoresNulosExcepcion;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public record FechaFin(LocalDate value) {

    public FechaFin {
        if (value == null) {
            throw ValoresNulosExcepcion.parametroNulo("fechaFin");
        }
    }

    public static FechaFin fromString(final String rawDate) {
        if (rawDate == null) {
            throw ValoresNulosExcepcion.parametroNulo("fechaFin");
        }
        try {
            return new FechaFin(LocalDate.parse(rawDate.trim()));
        } catch (final DateTimeParseException ex) {
            throw FechaInvalidaExcepcion.formatoFecha("fechaFin", rawDate);
        }
    }

    public boolean isBefore(final FechaInicio inicio) {
        return value.isBefore(inicio.value());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}