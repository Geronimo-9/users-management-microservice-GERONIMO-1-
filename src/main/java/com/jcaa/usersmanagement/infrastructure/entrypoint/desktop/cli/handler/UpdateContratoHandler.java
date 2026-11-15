package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ContratoNotFoundException;
import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ContratoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ContratoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ContratoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateContratoRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateContratoHandler implements OperationHandler {

  private final ContratoController contratoController;
  private final ConsoleIO console;
  private final ContratoResponsePrinter printer;

  @Override
  public void handle() {
    final int id = console.readInt("Contrato ID                                         : ");
    final String idUsuario = console.readRequired("User ID                                             : ");
    final String empresa = console.readRequired("Empresa                                             : ");
    final String empleado = console.readRequired("Empleado                                            : ");
    final String funciones = console.readRequired("Funciones                                           : ");
    final BigDecimal monto = new BigDecimal(console.readRequired("Monto                                               : "));
    final String frecuencia = console.readRequired("Frecuencia (SEMANAL/QUINCENAL/MENSUAL/SEMESTRAL/ANUAL): ");
    final LocalDate fechaFirma = LocalDate.parse(console.readRequired("Fecha Firma (YYYY-MM-DD)                            : "));
    final LocalDate fechaInicio = LocalDate.parse(console.readRequired("Fecha Inicio (YYYY-MM-DD)                           : "));
    final LocalDate fechaFin = LocalDate.parse(console.readRequired("Fecha Fin (YYYY-MM-DD)                              : "));
    final String estado = console.readRequired("Estado (VIGENTE / VENCIDO)                          : ");

    try {
      final ContratoResponse updated =
              contratoController.updateContrato(
                      new UpdateContratoRequest(
                              id,
                              idUsuario,
                              empresa,
                              empleado,
                              funciones,
                              monto,
                              frecuencia,
                              fechaFirma,
                              fechaInicio,
                              fechaFin,
                              estado));
      console.println("\n  Contrato updated successfully.");
      printer.print(updated);
    } catch (final ContratoNotFoundException | UserNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}