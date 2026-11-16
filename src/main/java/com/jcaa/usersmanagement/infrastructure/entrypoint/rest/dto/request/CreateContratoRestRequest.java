package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateContratoRestRequest(
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
        @NotNull(message = "fechaFin must not be null") LocalDate fechaFin) {}