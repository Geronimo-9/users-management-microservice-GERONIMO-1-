package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetContratoByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetContratoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetContratoByIdQuery;
import com.jcaa.usersmanagement.domain.exception.ContratoNotFoundException;
import com.jcaa.usersmanagement.domain.model.Contrato;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetContratoByIdService implements GetContratoByIdUseCase {

  private final GetContratoByIdPort getContratoByIdPort;
  private final Validator validator;

  @Override
  public Contrato execute(final GetContratoByIdQuery query) {
    validateQuery(query);

    return getContratoByIdPort
            .getById(query.id())
            .orElseThrow(
                    () -> ContratoNotFoundException.becauseIdWasNotFound(String.valueOf(query.id())));
  }

  private void validateQuery(final GetContratoByIdQuery query) {
    final Set<ConstraintViolation<GetContratoByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}