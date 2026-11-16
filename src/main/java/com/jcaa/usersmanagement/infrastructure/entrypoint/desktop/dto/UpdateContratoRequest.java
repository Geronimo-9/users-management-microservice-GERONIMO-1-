package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateContratoRequest(
        Integer id,
        String idUsuario,
        String empresa,
        String empleado,
        String funciones,
        BigDecimal monto,
        String frecuenciaPago,
        LocalDate fechaFirma,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String estado) {}