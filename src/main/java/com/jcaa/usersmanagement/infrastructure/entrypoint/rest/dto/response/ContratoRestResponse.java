package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratoRestResponse(
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