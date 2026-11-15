package com.jcaa.usersmanagement.domain.exception;

public final class ValoresNegativosExcepcion extends DomainException {

  private static final String VALORES_NEGATIVOS =
          "El campo '%s' no puede ser negativo (recibido: %s).";

  private ValoresNegativosExcepcion(final String message) {
    super(message);
  }

  public static ValoresNegativosExcepcion valorNegativo(final double valor) {
    return new ValoresNegativosExcepcion(String.format(VALORES_NEGATIVOS, "monto", valor));
  }

  public static ValoresNegativosExcepcion valorNegativo(final String campo, final Object valor) {
    return new ValoresNegativosExcepcion(String.format(VALORES_NEGATIVOS, campo, valor));
  }
}