package com.jcaa.usersmanagement.domain.valueobject.contrato;

import com.jcaa.usersmanagement.domain.exception.FormatoInvalidoExcepcion;
import com.jcaa.usersmanagement.domain.exception.ParametrosVaciosExcepcion;
import com.jcaa.usersmanagement.domain.exception.ValoresNulosExcepcion;
import java.util.regex.Pattern;

public record Empresa(String value) {

    // Permite letras (con tildes/ñ), números, espacios y signos habituales de razón social (. , & -)
    private static final Pattern EMPRESA_PATTERN =
            Pattern.compile("^[\\p{L}][\\p{L}0-9 .,&-]*$");

    public Empresa {
        if (value == null) {
            throw ValoresNulosExcepcion.parametroNulo("empresa");
        }
        final String normalized = value.trim();
        if (normalized.isEmpty()) {
            throw ParametrosVaciosExcepcion.parametroVacio("empresa");
        }
        if (!EMPRESA_PATTERN.matcher(normalized).matches()) {
            throw FormatoInvalidoExcepcion.formatoIncorrecto("empresa", normalized);
        }
        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}