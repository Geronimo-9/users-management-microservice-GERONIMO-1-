package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ContratoNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ContratoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteContratoHandler implements OperationHandler {

  private final ContratoController contratoController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final int id = console.readInt("Contrato ID to delete: ");
    try {
      contratoController.deleteContrato(id);
      console.println("  Contrato deleted successfully.");
    } catch (final ContratoNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}