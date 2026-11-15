package com.jcaa.usersmanagement.domain.valueobject.contrato;

import com.jcaa.usersmanagement.domain.exception.FormatoInvalidoExcepcion;
import com.jcaa.usersmanagement.domain.exception.ValoresNegativosExcepcion;
import com.jcaa.usersmanagement.domain.exception.ValoresNulosExcepcion;
import java.math.BigDecimal;
import java.math.RoundingMode;

public record Monto(BigDecimal value) {

    public Monto {
        if (value == null) {
            throw ValoresNulosExcepcion.parametroNulo("monto");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw ValoresNegativosExcepcion.valorNegativo("monto", value);
        }
        value = value.setScale(2, RoundingMode.HALF_UP);
    }

    public static Monto of(final double amount) {
        return new Monto(BigDecimal.valueOf(amount));
    }

    public static Monto fromString(final String rawAmount) {
        if (rawAmount == null) {
            throw ValoresNulosExcepcion.parametroNulo("monto");
        }
        try {
            return new Monto(new BigDecimal(rawAmount.trim()));
        } catch (final NumberFormatException ex) {
            throw FormatoInvalidoExcepcion.formatoIncorrecto("monto", rawAmount);
        }
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}