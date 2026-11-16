package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreateContratoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteContratoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllContratoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetContratoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateContratoUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.CreateContratoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteContratoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateContratoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetContratoByIdQuery;
import com.jcaa.usersmanagement.domain.model.Contrato;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateContratoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdatContratoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.ContratoRestResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper.ContratoRestMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contratos")
@RequiredArgsConstructor
public class ContratoRestController implements ContratoRestControllerDocs {

  private final CreateContratoUseCase createContratoUseCase;
  private final UpdateContratoUseCase updateContratoUseCase;
  private final DeleteContratoUseCase deleteContratoUseCase;
  private final GetContratoByIdUseCase getContratoByIdUseCase;
  private final GetAllContratoUseCase getAllContratoUseCase;

  @Override
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ContratoRestResponse create(@Valid @RequestBody final CreateContratoRestRequest request) {
    final CreateContratoCommand command = ContratoRestMapper.toCreateCommand(request);
    final Contrato contrato = createContratoUseCase.execute(command);
    return ContratoRestMapper.toResponse(contrato);
  }

  @Override
  @GetMapping
  public List<ContratoRestResponse> getAll() {
    return ContratoRestMapper.toResponseList(getAllContratoUseCase.execute());
  }

  @Override
  @GetMapping("/{id}")
  public ContratoRestResponse getById(@PathVariable final Integer id) {
    final GetContratoByIdQuery query = ContratoRestMapper.toGetByIdQuery(id);
    final Contrato contrato = getContratoByIdUseCase.execute(query);
    return ContratoRestMapper.toResponse(contrato);
  }

  @Override
  @PutMapping("/{id}")
  public ContratoRestResponse update(
          @PathVariable final Integer id,
          @Valid @RequestBody final UpdatContratoRestRequest request) {
    final UpdateContratoCommand command = ContratoRestMapper.toUpdateCommand(id, request);
    final Contrato contrato = updateContratoUseCase.execute(command);
    return ContratoRestMapper.toResponse(contrato);
  }

  @Override
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final Integer id) {
    final DeleteContratoCommand command = ContratoRestMapper.toDeleteCommand(id);
    deleteContratoUseCase.execute(command);
  }
}