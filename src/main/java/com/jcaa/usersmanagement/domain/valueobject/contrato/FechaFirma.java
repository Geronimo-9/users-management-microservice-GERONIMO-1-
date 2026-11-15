package com.jcaa.usersmanagement.domain.valueobject.contrato;

import com.jcaa.usersmanagement.domain.exception.FechaInvalidaExcepcion;
import com.jcaa.usersmanagement.domain.exception.ValoresNulosExcepcion;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public record FechaFirma(LocalDate value) {

    public FechaFirma {
        if (value == null) {
            throw ValoresNulosExcepcion.parametroNulo("fechaFirma");
        }
    }

    public static FechaFirma fromString(final String rawDate) {
        if (rawDate == null) {
            throw ValoresNulosExcepcion.parametroNulo("fechaFirma");
        }
        try {
            return new FechaFirma(LocalDate.parse(rawDate.trim()));
        } catch (final DateTimeParseException ex) {
            throw FechaInvalidaExcepcion.formatoFecha("fechaFirma", rawDate);
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}