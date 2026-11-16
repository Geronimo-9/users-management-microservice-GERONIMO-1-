package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DeleteContratoCommand(
        @NotNull(message = "id must not be null")
        @Positive(message = "id must be positive")
        Integer id) {}