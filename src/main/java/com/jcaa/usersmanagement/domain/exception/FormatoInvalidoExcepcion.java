package com.jcaa.usersmanagement.domain.exception;

public final class FormatoInvalidoExcepcion extends DomainException {

    private static final String FORMATO_INCORRECTO =
            "El valor '%s' del campo '%s' contiene caracteres o formato inválido.";
    private static final String VALOR_NO_PERMITIDO =
            "El valor '%s' no es válido para el campo '%s'.";

    private FormatoInvalidoExcepcion(final String message) {
        super(message);
    }

    public static FormatoInvalidoExcepcion formatoIncorrecto(final String invalidValue) {
        return new FormatoInvalidoExcepcion(
                String.format("El valor '%s' contiene caracteres inválidos.", invalidValue));
    }

    public static FormatoInvalidoExcepcion formatoIncorrecto(
            final String campo, final String invalidValue) {
        return new FormatoInvalidoExcepcion(String.format(FORMATO_INCORRECTO, invalidValue, campo));
    }

    public static FormatoInvalidoExcepcion valorNoPermitido(
            final String campo, final String invalidValue) {
        return new FormatoInvalidoExcepcion(String.format(VALOR_NO_PERMITIDO, invalidValue, campo));
    }
}