package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateContratoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteContratoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllContratoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetContratoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateContratoUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ContratoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateContratoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateContratoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.ContratoDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ContratoController {

  private final CreateContratoUseCase createContratoUseCase;
  private final UpdateContratoUseCase updateContratoUseCase;
  private final DeleteContratoUseCase deleteContratoUseCase;
  private final GetContratoByIdUseCase getContratoByIdUseCase;
  private final GetAllContratoUseCase getAllContratoUseCase;

  public List<ContratoResponse> listAllContratos() {
    final var contratos = getAllContratoUseCase.execute();
    return ContratoDesktopMapper.toResponseList(contratos);
  }

  public ContratoResponse findContratoById(final Integer id) {
    final var query = ContratoDesktopMapper.toGetByIdQuery(id);
    final var contrato = getContratoByIdUseCase.execute(query);
    return ContratoDesktopMapper.toResponse(contrato);
  }

  public ContratoResponse createContrato(final CreateContratoRequest request) {
    final var command = ContratoDesktopMapper.toCreateCommand(request);
    final var contrato = createContratoUseCase.execute(command);
    return ContratoDesktopMapper.toResponse(contrato);
  }

  public ContratoResponse updateContrato(final UpdateContratoRequest request) {
    final var command = ContratoDesktopMapper.toUpdateCommand(request);
    final var contrato = updateContratoUseCase.execute(command);
    return ContratoDesktopMapper.toResponse(contrato);
  }

  public void deleteContrato(final Integer id) {
    final var command = ContratoDesktopMapper.toDeleteCommand(id);
    deleteContratoUseCase.execute(command);
  }
}