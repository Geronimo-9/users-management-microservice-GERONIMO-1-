package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateContratoCommand;
import com.jcaa.usersmanagement.domain.model.Contrato;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateContratoUseCase {
  Contrato execute(@NotNull @Valid CreateContratoCommand command);
}