package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.out.EmailSenderPort;
import com.jcaa.usersmanagement.domain.exception.EmailSenderException;
import com.jcaa.usersmanagement.domain.model.Contrato;
import com.jcaa.usersmanagement.domain.model.EmailDestinationModel;
import com.jcaa.usersmanagement.domain.model.UserModel;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationService {

  private static final String SUBJECT_CREATED = "Tu cuenta ha sido creada — Gestion de Usuarios";
  private static final String SUBJECT_UPDATED = "Tu cuenta ha sido actualizada — Gestion de Usuarios";
  private static final String SUBJECT_CONTRATO_CREATED = "Nuevo contrato registrado — Gestion de Contratos";
  private static final String SUBJECT_CONTRATO_UPDATED = "Contrato actualizado — Gestion de Contratos";

  private static final String TOKEN_NAME = "name";
  private static final String TOKEN_EMAIL = "email";
  private static final String TOKEN_PASSWORD = "password";
  private static final String TOKEN_ROLE = "role";
  private static final String TOKEN_STATUS = "status";

  private static final String TOKEN_EMPRESA = "empresa";
  private static final String TOKEN_EMPLEADO = "empleado";
  private static final String TOKEN_MONTO = "monto";
  private static final String TOKEN_FRECUENCIA = "frecuenciaPago";
  private static final String TOKEN_FECHA_INICIO = "fechaInicio";
  private static final String TOKEN_FECHA_FIN = "fechaFin";
  private static final String TOKEN_ESTADO = "estado";

  private static final String LOG_SEND_FAILED =
          "[EmailNotificationService] correo no enviado. Causa: {}";

  private final EmailSenderPort emailSenderPort;

  @Async("notificationExecutor")
  public void notifyUserCreated(final UserModel user, final String plainPassword) {
    final String template = loadTemplate("user-created.html");
    final String body =
            renderTemplate(
                    template,
                    Map.of(
                            TOKEN_NAME, user.getName().value(),
                            TOKEN_EMAIL, user.getEmail().value(),
                            TOKEN_PASSWORD, plainPassword,
                            TOKEN_ROLE, user.getRole().name()));
    sendOrLog(buildDestination(user, SUBJECT_CREATED, body));
  }

  @Async("notificationExecutor")
  public void notifyUserUpdated(final UserModel user) {
    final String template = loadTemplate("user-updated.html");
    final String body =
            renderTemplate(
                    template,
                    Map.of(
                            TOKEN_NAME, user.getName().value(),
                            TOKEN_EMAIL, user.getEmail().value(),
                            TOKEN_ROLE, user.getRole().name(),
                            TOKEN_STATUS, user.getStatus().name()));
    sendOrLog(buildDestination(user, SUBJECT_UPDATED, body));
  }

  @Async("notificationExecutor")
  public void notifyContratoCreated(final Contrato contrato, final UserModel user) {
    final String template = loadTemplate("contrato-created.html");
    final String body =
            renderTemplate(
                    template,
                    Map.of(
                            TOKEN_EMPLEADO, contrato.getEmpleado().value(),
                            TOKEN_EMPRESA, contrato.getEmpresa().value(),
                            TOKEN_MONTO, contrato.getMonto().toString(),
                            TOKEN_FRECUENCIA, contrato.getFrecuenciaPago().name(),
                            TOKEN_FECHA_INICIO, contrato.getFechaInicio().toString(),
                            TOKEN_FECHA_FIN, contrato.getFechaFin().toString(),
                            TOKEN_ESTADO, contrato.getEstado().name()));
    sendOrLog(buildDestination(user, SUBJECT_CONTRATO_CREATED, body));
  }

  @Async("notificationExecutor")
  public void notifyContratoUpdated(final Contrato contrato, final UserModel user) {
    final String template = loadTemplate("contrato-updated.html");
    final String body =
            renderTemplate(
                    template,
                    Map.of(
                            TOKEN_EMPLEADO, contrato.getEmpleado().value(),
                            TOKEN_EMPRESA, contrato.getEmpresa().value(),
                            TOKEN_MONTO, contrato.getMonto().toString(),
                            TOKEN_FRECUENCIA, contrato.getFrecuenciaPago().name(),
                            TOKEN_FECHA_INICIO, contrato.getFechaInicio().toString(),
                            TOKEN_FECHA_FIN, contrato.getFechaFin().toString(),
                            TOKEN_ESTADO, contrato.getEstado().name()));
    sendOrLog(buildDestination(user, SUBJECT_CONTRATO_UPDATED, body));
  }

  private static EmailDestinationModel buildDestination(
          final UserModel user, final String subject, final String body) {
    return new EmailDestinationModel(
            user.getEmail().value(), user.getName().value(), subject, body);
  }

  private String loadTemplate(final String templateName) {
    final String path = "/templates/" + templateName;
    try (InputStream inputStream = openResourceStream(path)) {
      if (Objects.isNull(inputStream)) {
        throw EmailSenderException.becauseSendFailed(
                new IllegalStateException("Template not found: " + path));
      }
      return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    } catch (final IOException ioException) {
      throw EmailSenderException.becauseSendFailed(ioException);
    }
  }

  InputStream openResourceStream(final String path) {
    return getClass().getResourceAsStream(path);
  }

  private String renderTemplate(final String template, final Map<String, String> values) {
    String result = template;
    for (final Map.Entry<String, String> tokenEntry : values.entrySet()) {
      final String token = "{{" + tokenEntry.getKey() + "}}";
      result = result.replace(token, tokenEntry.getValue());
    }
    return result;
  }

  private void sendOrLog(final EmailDestinationModel destination) {
    try {
      emailSenderPort.send(destination);
    } catch (final EmailSenderException senderException) {
      log.warn(LOG_SEND_FAILED, senderException.getMessage(), senderException);
      throw senderException;
    }
  }
}