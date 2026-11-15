package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateContratoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetContratoByIdPort;
import com.jcaa.usersmanagement.application.port.out.GetUserByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateContratoPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateContratoCommand;
import com.jcaa.usersmanagement.application.service.mapper.ContratoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ContratoNotFoundException;
import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import com.jcaa.usersmanagement.domain.model.Contrato;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateContratoService implements UpdateContratoUseCase {

  private final UpdateContratoPort updateContratoPort;
  private final GetContratoByIdPort getContratoByIdPort;
  private final GetUserByIdPort getUserByIdPort;
  private final EmailNotificationService emailNotificationService;
  private final Validator validator;

  @Override
  public Contrato execute(final UpdateContratoCommand command) {
    validateCommand(command);

    ensureContratoExists(command.id());
    final UserId userId = new UserId(command.idUsuario());
    final UserModel user = findExistingUserOrFail(userId);

    final Contrato contratoToUpdate = ContratoApplicationMapper.fromUpdateCommandToModel(command);
    final Contrato updatedContrato = updateContratoPort.update(contratoToUpdate);

    emailNotificationService.notifyContratoUpdated(updatedContrato, user);

    return updatedContrato;
  }

  private void validateCommand(final UpdateContratoCommand command) {
    final Set<ConstraintViolation<UpdateContratoCommand>> violations = validator.validate(command);
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

  private UserModel findExistingUserOrFail(final UserId userId) {
    return getUserByIdPort
            .getById(userId)
            .orElseThrow(() -> UserNotFoundException.becauseIdWasNotFound(userId.value()));
  }
}