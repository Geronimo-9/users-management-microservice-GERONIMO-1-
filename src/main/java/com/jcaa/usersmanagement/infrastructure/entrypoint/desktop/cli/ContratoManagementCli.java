package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateContratoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteContratoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindContratoByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListContratoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateContratoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ContratoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ContratoController;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ContratoManagementCli {

  private static final String BANNER =
          """
          ==========================================
              Contratos Management System
          ==========================================""";

  private static final String MENU_BORDER = "  ==========================================";

  private final ContratoController contratoController;
  private final ConsoleIO console;

  public void start() {
    console.println(BANNER);
    final ContratoResponsePrinter printer = new ContratoResponsePrinter(console);
    runLoop(buildHandlers(printer));
  }

  private void runLoop(final Map<Integer, OperationHandler> handlers) {
    boolean running = true;
    while (running) {
      printMenu();
      final int choice = console.readInt("\n  Option: ");
      if (choice == 0) {
        console.println("\n  Goodbye!\n");
        running = false;
      } else if (handlers.containsKey(choice)) {
        executeHandler(handlers.get(choice));
      } else {
        console.println("  Invalid option. Please try again.");
      }
    }
  }

  private void executeHandler(final OperationHandler handler) {
    try {
      handler.handle();
    } catch (final ConstraintViolationException exception) {
      console.println("  Validation errors:");
      exception
              .getConstraintViolations()
              .forEach(violation -> console.println("    - " + violation.getMessage()));
    } catch (final RuntimeException exception) {
      console.println("  Unexpected error: " + exception.getMessage());
    }
  }

  private Map<Integer, OperationHandler> buildHandlers(final ContratoResponsePrinter printer) {
    return Map.of(
            1, new ListContratoHandler(contratoController, printer),
            2, new FindContratoByIdHandler(contratoController, console, printer),
            3, new CreateContratoHandler(contratoController, console, printer),
            4, new UpdateContratoHandler(contratoController, console, printer),
            5, new DeleteContratoHandler(contratoController, console));
  }

  private void printMenu() {
    console.println();
    console.println(MENU_BORDER);
    console.println("    Contratos Menu");
    console.println(MENU_BORDER);
    console.println("    [1] List all contratos");
    console.println("    [2] Find contrato by ID");
    console.println("    [3] Create contrato");
    console.println("    [4] Update contrato");
    console.println("    [5] Delete contrato");
    console.println("    [0] Exit");
    console.println(MENU_BORDER);
  }
}