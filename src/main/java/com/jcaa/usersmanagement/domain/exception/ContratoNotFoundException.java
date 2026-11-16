package com.jcaa.usersmanagement.domain.exception;

public final class ContratoNotFoundException extends DomainException {

    private static final String MESSAGE_BY_ID = "El contrato con id '%s' no fue encontrado.";

    private ContratoNotFoundException(final String message) {
        super(message);
    }

    public static ContratoNotFoundException becauseIdWasNotFound(final String contratoId) {
        return new ContratoNotFoundException(String.format(MESSAGE_BY_ID, contratoId));
    }
}