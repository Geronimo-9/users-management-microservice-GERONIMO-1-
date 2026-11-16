package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateContratoCommand(
        @NotNull(message = "id must not be null")
        @Positive(message = "id must be positive")
        Integer id,
        @NotBlank(message = "idUsuario must not be blank") String idUsuario,
        @NotBlank(message = "empresa must not be blank") String empresa,
        @NotBlank(message = "empleado must not be blank") String empleado,
        @NotBlank(message = "funciones must not be blank") String funciones,
        @NotNull(message = "monto must not be null")
        @DecimalMin(value = "0.0", inclusive = true, message = "monto must be >= 0")
        BigDecimal monto,
        @NotBlank(message = "frecuenciaPago must not be blank") String frecuenciaPago,
        @NotNull(message = "fechaFirma must not be null") LocalDate fechaFirma,
        @NotNull(message = "fechaInicio must not be null") LocalDate fechaInicio,
        @NotNull(message = "fechaFin must not be null") LocalDate fechaFin,
        @NotBlank(message = "estado must not be blank") String estado) {}