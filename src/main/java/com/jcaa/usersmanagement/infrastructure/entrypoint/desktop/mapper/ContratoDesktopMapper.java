package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateContratoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteContratoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateContratoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetContratoByIdQuery;
import com.jcaa.usersmanagement.domain.model.Contrato;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ContratoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateContratoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateContratoRequest;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContratoDesktopMapper {

  public CreateContratoCommand toCreateCommand(final CreateContratoRequest request) {
    return new CreateContratoCommand(
            request.idUsuario(),
            request.empresa(),
            request.empleado(),
            request.funciones(),
            request.monto(),
            request.frecuenciaPago(),
            request.fechaFirma(),
            request.fechaInicio(),
            request.fechaFin());
  }

  public UpdateContratoCommand toUpdateCommand(final UpdateContratoRequest request) {
    return new UpdateContratoCommand(
            request.id(),
            request.idUsuario(),
            request.empresa(),
            request.empleado(),
            request.funciones(),
            request.monto(),
            request.frecuenciaPago(),
            request.fechaFirma(),
            request.fechaInicio(),
            request.fechaFin(),
            request.estado());
  }

  public DeleteContratoCommand toDeleteCommand(final Integer id) {
    return new DeleteContratoCommand(id);
  }

  public GetContratoByIdQuery toGetByIdQuery(final Integer id) {
    return new GetContratoByIdQuery(id);
  }

  public ContratoResponse toResponse(final Contrato contrato) {
    return new ContratoResponse(
            contrato.getId(),
            contrato.getIdUsuario().value(),
            contrato.getEmpresa().value(),
            contrato.getEmpleado().value(),
            contrato.getFunciones().value(),
            contrato.getMonto().value(),
            contrato.getFrecuenciaPago().name(),
            contrato.getFechaFirma().value(),
            contrato.getFechaInicio().value(),
            contrato.getFechaFin().value(),
            contrato.getEstado().name());
  }

  public List<ContratoResponse> toResponseList(final List<Contrato> contratos) {
    return contratos.stream().map(ContratoDesktopMapper::toResponse).toList();
  }
}