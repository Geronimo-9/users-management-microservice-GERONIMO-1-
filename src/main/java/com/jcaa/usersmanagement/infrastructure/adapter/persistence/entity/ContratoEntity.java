package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratoEntity(
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
        String estado,
        String createdAt,
        String updatedAt) {}