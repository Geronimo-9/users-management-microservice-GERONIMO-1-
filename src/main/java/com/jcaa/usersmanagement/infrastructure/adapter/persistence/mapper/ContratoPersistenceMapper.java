package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

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
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ContratoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.ContratoEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContratoPersistenceMapper {

  public ContratoPersistenceDto fromModelToDto(final Contrato contrato) {
    return new ContratoPersistenceDto(
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
            contrato.getEstado().name(),
            null,
            null);
  }

  public ContratoEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    return new ContratoEntity(
            resultSet.getInt("id"),
            resultSet.getString("id_usuario"),
            resultSet.getString("empresa"),
            resultSet.getString("empleado"),
            resultSet.getString("funciones"),
            resultSet.getBigDecimal("monto"),
            resultSet.getString("frecuencia_pago"),
            resultSet.getDate("fecha_firma").toLocalDate(),
            resultSet.getDate("fecha_inicio").toLocalDate(),
            resultSet.getDate("fecha_fin").toLocalDate(),
            resultSet.getString("estado"),
            resultSet.getString("created_at"),
            resultSet.getString("updated_at"));
  }

  public Contrato fromEntityToModel(final ContratoEntity entity) {
    return new Contrato(
            entity.id(),
            new UserId(entity.idUsuario()),
            new Empresa(entity.empresa()),
            new Empleado(entity.empleado()),
            new Funciones(entity.funciones()),
            new Monto(entity.monto()),
            FrecuenciaPago.fromString(entity.frecuenciaPago()),
            new FechaFirma(entity.fechaFirma()),
            new FechaInicio(entity.fechaInicio()),
            new FechaFin(entity.fechaFin()),
            Estado.fromString(entity.estado()));
  }

  public Contrato fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<Contrato> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
    final List<Contrato> contratos = new ArrayList<>();
    while (resultSet.next()) {
      contratos.add(fromResultSetToModel(resultSet));
    }
    return contratos;
  }
}