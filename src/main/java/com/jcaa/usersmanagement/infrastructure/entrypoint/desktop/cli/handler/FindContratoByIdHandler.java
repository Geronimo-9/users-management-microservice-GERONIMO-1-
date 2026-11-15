package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ContratoNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ContratoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ContratoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ContratoResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindContratoByIdHandler implements OperationHandler {

  private final ContratoController contratoController;
  private final ConsoleIO console;
  private final ContratoResponsePrinter printer;

  @Override
  public void handle() {
    final int id = console.readInt("Contrato ID: ");
    try {
      final ContratoResponse contrato = contratoController.findContratoById(id);
      printer.print(contrato);
    } catch (final ContratoNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}