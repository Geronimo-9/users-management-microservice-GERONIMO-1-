package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Contrato;
import java.util.Optional;

public interface GetContratoByIdPort {
  Optional<Contrato> getById(Integer contratoId);
}