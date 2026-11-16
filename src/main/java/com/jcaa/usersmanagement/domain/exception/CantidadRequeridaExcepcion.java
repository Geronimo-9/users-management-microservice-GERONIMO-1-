package com.jcaa.usersmanagement.domain.exception;

public final class CantidadRequeridaExcepcion extends DomainException {

    private static final String CANTIDAD_REQUERIDA = "La cantidad '%s' de parámetros debe ser 10.";
    private static final String FUERA_DE_RANGO =
            "El parámetro '%s' excede la cantidad de dígitos disponibles.";

    private CantidadRequeridaExcepcion(final String mensaje) {
        super(mensaje);
    }

    public static CantidadRequeridaExcepcion cantidadRequerida(final String valorInvalido) {
        return new CantidadRequeridaExcepcion(String.format(CANTIDAD_REQUERIDA, valorInvalido));
    }

    public static CantidadRequeridaExcepcion fueraDeRango(final String valorInvalido) {
        return new CantidadRequeridaExcepcion(String.format(FUERA_DE_RANGO, valorInvalido));
    }
}