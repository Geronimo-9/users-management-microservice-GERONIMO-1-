package com.jcaa.usersmanagement.domain.valueobject.contrato;

import com.jcaa.usersmanagement.domain.exception.ParametrosVaciosExcepcion;
import com.jcaa.usersmanagement.domain.exception.ValoresNulosExcepcion;

public record Funciones(String value) {

    public Funciones {
        if (value == null) {
            throw ValoresNulosExcepcion.parametroNulo("funciones");
        }
        final String normalized = value.trim();
        if (normalized.isEmpty()) {
            throw ParametrosVaciosExcepcion.parametroVacio("funciones");
        }
        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}