package com.jcaa.usersmanagement.domain.exception;

public final class FechaInvalidaExcepcion extends DomainException {

    private static final String PLAZOS_INCONSISTENTES =
            "Los plazos presentan inconsistencias. La fecha de fin no puede ser anterior a la fecha de inicio.";
    private static final String FIRMA_POSTERIOR_A_FIN =
            "La fecha de firma no puede ser posterior a la fecha de finalización del contrato.";
    private static final String FORMATO_FECHA =
            "La fecha '%s' del campo '%s' no tiene un formato válido (AAAA-MM-DD).";

    private FechaInvalidaExcepcion(final String message) {
        super(message);
    }

    public static FechaInvalidaExcepcion fechaInconsistente() {
        return new FechaInvalidaExcepcion(PLAZOS_INCONSISTENTES);
    }

    public static FechaInvalidaExcepcion firmaPosteriorAFin() {
        return new FechaInvalidaExcepcion(FIRMA_POSTERIOR_A_FIN);
    }

    public static FechaInvalidaExcepcion formatoFecha(final String campo, final String valor) {
        return new FechaInvalidaExcepcion(String.format(FORMATO_FECHA, valor, campo));
    }
}