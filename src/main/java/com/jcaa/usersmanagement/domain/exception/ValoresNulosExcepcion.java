package com.jcaa.usersmanagement.domain.exception;

public final class ValoresNulosExcepcion extends DomainException {

    private static final String PARAMETRO_NULO = "El parámetro '%s' no puede ser nulo.";

    private ValoresNulosExcepcion(final String message) {
        super(message);
    }

    public static ValoresNulosExcepcion parametroNulo(final String campo) {
        return new ValoresNulosExcepcion(String.format(PARAMETRO_NULO, campo));
    }
}