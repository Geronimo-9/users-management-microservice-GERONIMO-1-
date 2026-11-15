package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetContratoByIdQuery;
import com.jcaa.usersmanagement.domain.model.Contrato;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetContratoByIdUseCase {
  Contrato execute(@NotNull @Valid GetContratoByIdQuery query);
}