package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteContratoPort;
import com.jcaa.usersmanagement.application.port.out.GetAllContratoPort;
import com.jcaa.usersmanagement.application.port.out.GetContratoByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveContratoPort;
import com.jcaa.usersmanagement.application.port.out.UpdateContratoPort;
import com.jcaa.usersmanagement.domain.exception.ContratoNotFoundException;
import com.jcaa.usersmanagement.domain.model.Contrato;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ContratoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.ContratoPersistenceMapper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ContratoRepositoryMySQL
        implements SaveContratoPort,
        UpdateContratoPort,
        GetContratoByIdPort,
        GetAllContratoPort,
        DeleteContratoPort {

  private static final String SQL_INSERT =
          "INSERT INTO contratos "
                  + "(id_usuario, empresa, empleado, funciones, monto, frecuencia_pago, "
                  + "fecha_firma, fecha_inicio, fecha_fin, estado, created_at, updated_at) "
                  + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
          "UPDATE contratos SET id_usuario = ?, empresa = ?, empleado = ?, funciones = ?, "
                  + "monto = ?, frecuencia_pago = ?, fecha_firma = ?, fecha_inicio = ?, "
                  + "fecha_fin = ?, estado = ?, updated_at = NOW() "
                  + "WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
          "SELECT id, id_usuario, empresa, empleado, funciones, monto, frecuencia_pago, "
                  + "fecha_firma, fecha_inicio, fecha_fin, estado, created_at, updated_at "
                  + "FROM contratos "
                  + "WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
          "SELECT id, id_usuario, empresa, empleado, funciones, monto, frecuencia_pago, "
                  + "fecha_firma, fecha_inicio, fecha_fin, estado, created_at, updated_at "
                  + "FROM contratos "
                  + "ORDER BY id DESC";

  private static final String SQL_DELETE =
          "DELETE FROM contratos "
                  + "WHERE id = ?";

  private final DataSource dataSource;

  @Override
  public Contrato save(final Contrato contrato) {
    final ContratoPersistenceDto dto = ContratoPersistenceMapper.fromModelToDto(contrato);
    final Integer generatedId = executeSave(dto);
    return findByIdOrFail(generatedId);
  }

  @Override
  public Contrato update(final Contrato contrato) {
    final ContratoPersistenceDto dto = ContratoPersistenceMapper.fromModelToDto(contrato);
    executeUpdate(dto);
    return findByIdOrFail(contrato.getId());
  }

  @Override
  public Optional<Contrato> getById(final Integer contratoId) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setInt(1, contratoId);
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(ContratoPersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseContratoFindByIdFailed(contratoId, exception);
    }
  }

  @Override
  public List<Contrato> getAll() {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return ContratoPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseContratoFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final Integer contratoId) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setInt(1, contratoId);
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseContratoDeleteFailed(contratoId, exception);
    }
  }

  private Integer executeSave(final ContratoPersistenceDto dto) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement =
                 connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, dto.idUsuario());
      statement.setString(2, dto.empresa());
      statement.setString(3, dto.empleado());
      statement.setString(4, dto.funciones());
      statement.setBigDecimal(5, dto.monto());
      statement.setString(6, dto.frecuenciaPago());
      statement.setDate(7, Date.valueOf(dto.fechaFirma()));
      statement.setDate(8, Date.valueOf(dto.fechaInicio()));
      statement.setDate(9, Date.valueOf(dto.fechaFin()));
      statement.setString(10, dto.estado());
      statement.executeUpdate();

      try (final ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          return generatedKeys.getInt(1);
        }
        throw new SQLException("Creating contrato failed, no ID obtained.");
      }
    } catch (final SQLException exception) {
      throw PersistenceException.becauseContratoSaveFailed(dto.idUsuario(), exception);
    }
  }

  private void executeUpdate(final ContratoPersistenceDto dto) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.idUsuario());
      statement.setString(2, dto.empresa());
      statement.setString(3, dto.empleado());
      statement.setString(4, dto.funciones());
      statement.setBigDecimal(5, dto.monto());
      statement.setString(6, dto.frecuenciaPago());
      statement.setDate(7, Date.valueOf(dto.fechaFirma()));
      statement.setDate(8, Date.valueOf(dto.fechaInicio()));
      statement.setDate(9, Date.valueOf(dto.fechaFin()));
      statement.setString(10, dto.estado());
      statement.setInt(11, dto.id());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseContratoUpdateFailed(dto.id(), exception);
    }
  }

  private Contrato findByIdOrFail(final Integer contratoId) {
    return getById(contratoId)
            .orElseThrow(
                    () -> ContratoNotFoundException.becauseIdWasNotFound(String.valueOf(contratoId)));
  }
}