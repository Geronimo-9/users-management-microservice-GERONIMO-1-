package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateContratoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteContratoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateContratoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetContratoByIdQuery;
import com.jcaa.usersmanagement.domain.model.Contrato;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateContratoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdatContratoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.ContratoRestResponse;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContratoRestMapper {

  public CreateContratoCommand toCreateCommand(final CreateContratoRestRequest request) {
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

  public UpdateContratoCommand toUpdateCommand(
          final Integer id, final UpdatContratoRestRequest request) {
    return new UpdateContratoCommand(
            id,
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

  public GetContratoByIdQuery toGetByIdQuery(final Integer id) {
    return new GetContratoByIdQuery(id);
  }

  public DeleteContratoCommand toDeleteCommand(final Integer id) {
    return new DeleteContratoCommand(id);
  }

  public ContratoRestResponse toResponse(final Contrato contrato) {
    return new ContratoRestResponse(
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

  public List<ContratoRestResponse> toResponseList(final List<Contrato> contratos) {
    return contratos.stream().map(ContratoRestMapper::toResponse).toList();
  }
}