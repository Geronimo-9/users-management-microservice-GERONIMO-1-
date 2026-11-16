package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.Contrato;
import java.util.Map;
import lombok.Getter;

@Getter
public final class ContratoUpdatedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "contrato.updated";

  private final Contrato contrato;

  public ContratoUpdatedDomainEvent(final Contrato contrato) {
    super(EVENT_NAME);
    this.contrato = contrato;
  }

  @Override
  public Map<String, String> payload() {
    return Map.ofEntries(
            Map.entry("idUsuario", contrato.getIdUsuario().value()),
            Map.entry("empresa", contrato.getEmpresa().value()),
            Map.entry("empleado", contrato.getEmpleado().value()),
            Map.entry("funciones", contrato.getFunciones().value()),
            Map.entry("monto", contrato.getMonto().toString()),
            Map.entry("frecuenciaPago", contrato.getFrecuenciaPago().name()),
            Map.entry("fechaFirma", contrato.getFechaFirma().toString()),
            Map.entry("fechaInicio", contrato.getFechaInicio().toString()),
            Map.entry("fechaFin", contrato.getFechaFin().toString()),
            Map.entry("estado", contrato.getEstado().name()));
  }
}