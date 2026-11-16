package com.jcaa.usersmanagement.domain.exception;

public final class ParametrosVaciosExcepcion extends DomainException {

    private static final String PARAMETRO_VACIO = "El parámetro '%s' no puede quedar vacío.";

    private ParametrosVaciosExcepcion(final String message) {
        super(message);
    }

    public static ParametrosVaciosExcepcion parametroVacio(final String campo) {
        return new ParametrosVaciosExcepcion(String.format(PARAMETRO_VACIO, campo));
    }
}