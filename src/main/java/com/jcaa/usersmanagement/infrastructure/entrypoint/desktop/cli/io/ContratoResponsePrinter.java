package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ContratoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ContratoResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-16s : %s%n";

  private final ConsoleIO console;

  public void print(final ContratoResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID", response.id());
    console.printf(ROW_FORMAT, "ID Usuario", response.idUsuario());
    console.printf(ROW_FORMAT, "Empresa", response.empresa());
    console.printf(ROW_FORMAT, "Empleado", response.empleado());
    console.printf(ROW_FORMAT, "Funciones", response.funciones());
    console.printf(ROW_FORMAT, "Monto", response.monto());
    console.printf(ROW_FORMAT, "Frecuencia Pago", response.frecuenciaPago());
    console.printf(ROW_FORMAT, "Fecha Firma", response.fechaFirma());
    console.printf(ROW_FORMAT, "Fecha Inicio", response.fechaInicio());
    console.printf(ROW_FORMAT, "Fecha Fin", response.fechaFin());
    console.printf(ROW_FORMAT, "Estado", response.estado());
    console.println(SEPARATOR);
  }

  public void printList(final List<ContratoResponse> contratos) {
    if (contratos.isEmpty()) {
      console.println("  No contratos found.");
      return;
    }
    console.printf("%n  Total: %d contrato(s)%n", contratos.size());
    contratos.forEach(this::print);
  }
}