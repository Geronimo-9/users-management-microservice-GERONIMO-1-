package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ContratoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ContratoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ContratoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListContratoHandler implements OperationHandler {

  private final ContratoController contratoController;
  private final ContratoResponsePrinter printer;

  @Override
  public void handle() {
    final List<ContratoResponse> contratos = contratoController.listAllContratos();
    printer.printList(contratos);
  }
}