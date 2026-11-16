package com.jcaa.usersmanagement.domain.valueobject.contrato;

import com.jcaa.usersmanagement.domain.exception.FechaInvalidaExcepcion;
import com.jcaa.usersmanagement.domain.exception.ValoresNulosExcepcion;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public record FechaInicio(LocalDate value) {

    public FechaInicio {
        if (value == null) {
            throw ValoresNulosExcepcion.parametroNulo("fechaInicio");
        }
    }

    public static FechaInicio fromString(final String rawDate) {
        if (rawDate == null) {
            throw ValoresNulosExcepcion.parametroNulo("fechaInicio");
        }
        try {
            return new FechaInicio(LocalDate.parse(rawDate.trim()));
        } catch (final DateTimeParseException ex) {
            throw FechaInvalidaExcepcion.formatoFecha("fechaInicio", rawDate);
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}