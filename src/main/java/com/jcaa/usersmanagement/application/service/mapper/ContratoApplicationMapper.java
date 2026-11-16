package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateContratoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateContratoCommand;
import com.jcaa.usersmanagement.domain.enums.Estado;
import com.jcaa.usersmanagement.domain.enums.FrecuenciaPago;
import com.jcaa.usersmanagement.domain.model.Contrato;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import com.jcaa.usersmanagement.domain.valueobject.contrato.Empleado;
import com.jcaa.usersmanagement.domain.valueobject.contrato.Empresa;
import com.jcaa.usersmanagement.domain.valueobject.contrato.FechaFin;
import com.jcaa.usersmanagement.domain.valueobject.contrato.FechaFirma;
import com.jcaa.usersmanagement.domain.valueobject.contrato.FechaInicio;
import com.jcaa.usersmanagement.domain.valueobject.contrato.Funciones;
import com.jcaa.usersmanagement.domain.valueobject.contrato.Monto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContratoApplicationMapper {

  public Contrato fromCreateCommandToModel(final CreateContratoCommand command) {
    return Contrato.crear(
            new UserId(command.idUsuario()),
            new Empresa(command.empresa()),
            new Empleado(command.empleado()),
            new Funciones(command.funciones()),
            new Monto(command.monto()),
            FrecuenciaPago.fromString(command.frecuenciaPago()),
            new FechaFirma(command.fechaFirma()),
            new FechaInicio(command.fechaInicio()),
            new FechaFin(command.fechaFin()));
  }

  public Contrato fromUpdateCommandToModel(final UpdateContratoCommand command) {
    return new Contrato(
            command.id(),
            new UserId(command.idUsuario()),
            new Empresa(command.empresa()),
            new Empleado(command.empleado()),
            new Funciones(command.funciones()),
            new Monto(command.monto()),
            FrecuenciaPago.fromString(command.frecuenciaPago()),
            new FechaFirma(command.fechaFirma()),
            new FechaInicio(command.fechaInicio()),
            new FechaFin(command.fechaFin()),
            Estado.fromString(command.estado()));
  }
}