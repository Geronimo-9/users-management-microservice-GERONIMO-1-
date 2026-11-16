package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteContratoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteContratoPort;
import com.jcaa.usersmanagement.application.port.out.GetContratoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteContratoCommand;
import com.jcaa.usersmanagement.domain.exception.ContratoNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteContratoService implements DeleteContratoUseCase {

  private final DeleteContratoPort deleteContratoPort;
  private final GetContratoByIdPort getContratoByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteContratoCommand command) {
    validateCommand(command);

    ensureContratoExists(command.id());
    deleteContratoPort.delete(command.id());
  }

  private void validateCommand(final DeleteContratoCommand command) {
    final Set<ConstraintViolation<DeleteContratoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureContratoExists(final Integer contratoId) {
    getContratoByIdPort
            .getById(contratoId)
            .orElseThrow(
                    () -> ContratoNotFoundException.becauseIdWasNotFound(String.valueOf(contratoId)));
  }
}