package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ContratoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ContratoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ContratoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateContratoRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateContratoHandler implements OperationHandler {

  private final ContratoController contratoController;
  private final ConsoleIO console;
  private final ContratoResponsePrinter printer;

  @Override
  public void handle() {
    final String idUsuario = console.readRequired("User ID                                             : ");
    final String empresa = console.readRequired("Empresa                                             : ");
    final String empleado = console.readRequired("Empleado                                            : ");
    final String funciones = console.readRequired("Funciones                                           : ");
    final BigDecimal monto = new BigDecimal(console.readRequired("Monto                                               : "));
    final String frecuencia = console.readRequired("Frecuencia (SEMANAL/QUINCENAL/MENSUAL/SEMESTRAL/ANUAL): ");
    final LocalDate fechaFirma = LocalDate.parse(console.readRequired("Fecha Firma (YYYY-MM-DD)                            : "));
    final LocalDate fechaInicio = LocalDate.parse(console.readRequired("Fecha Inicio (YYYY-MM-DD)                           : "));
    final LocalDate fechaFin = LocalDate.parse(console.readRequired("Fecha Fin (YYYY-MM-DD)                              : "));

    try {
      final ContratoResponse created =
              contratoController.createContrato(
                      new CreateContratoRequest(
                              idUsuario,
                              empresa,
                              empleado,
                              funciones,
                              monto,
                              frecuencia,
                              fechaFirma,
                              fechaInicio,
                              fechaFin));
      console.println("\n  Contrato created successfully.");
      printer.print(created);
    } catch (final UserNotFoundException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}