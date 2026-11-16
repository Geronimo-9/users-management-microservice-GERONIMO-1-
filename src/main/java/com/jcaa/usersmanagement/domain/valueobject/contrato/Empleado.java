package com.jcaa.usersmanagement.domain.valueobject.contrato;

import com.jcaa.usersmanagement.domain.exception.FormatoInvalidoExcepcion;
import com.jcaa.usersmanagement.domain.exception.ParametrosVaciosExcepcion;
import com.jcaa.usersmanagement.domain.exception.ValoresNulosExcepcion;
import java.util.regex.Pattern;

public record Empleado(String value) {

    // Permite letras (con tildes/ñ) y espacios entre nombres/apellidos
    private static final Pattern EMPLEADO_PATTERN = Pattern.compile("^[\\p{L}][\\p{L} ]*$");

    public Empleado {
        if (value == null) {
            throw ValoresNulosExcepcion.parametroNulo("empleado");
        }
        final String normalized = value.trim();
        if (normalized.isEmpty()) {
            throw ParametrosVaciosExcepcion.parametroVacio("empleado");
        }
        if (!EMPLEADO_PATTERN.matcher(normalized).matches()) {
            throw FormatoInvalidoExcepcion.formatoIncorrecto("empleado", normalized);
        }
        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}