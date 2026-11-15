package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateContratoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetUserByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveContratoPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateContratoCommand;
import com.jcaa.usersmanagement.application.service.mapper.ContratoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import com.jcaa.usersmanagement.domain.model.Contrato;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateContratoService implements CreateContratoUseCase {

  private final SaveContratoPort saveContratoPort;
  private final GetUserByIdPort getUserByIdPort;
  private final EmailNotificationService emailNotificationService;
  private final Validator validator;

  @Override
  public Contrato execute(final CreateContratoCommand command) {
    validateCommand(command);

    final UserId userId = new UserId(command.idUsuario());
    final UserModel user = findExistingUserOrFail(userId);

    final Contrato contratoToSave = ContratoApplicationMapper.fromCreateCommandToModel(command);
    final Contrato savedContrato = saveContratoPort.save(contratoToSave);

    emailNotificationService.notifyContratoCreated(savedContrato, user);

    return savedContrato;
  }

  private void validateCommand(final CreateContratoCommand command) {
    final Set<ConstraintViolation<CreateContratoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private UserModel findExistingUserOrFail(final UserId userId) {
    return getUserByIdPort
            .getById(userId)
            .orElseThrow(() -> UserNotFoundException.becauseIdWasNotFound(userId.value()));
  }
}