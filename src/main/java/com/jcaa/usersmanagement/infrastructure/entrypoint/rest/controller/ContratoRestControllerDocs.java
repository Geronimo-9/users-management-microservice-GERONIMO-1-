package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.infrastructure.config.OpenApiConfig;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateContratoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdatContratoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.ApiErrorResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.ContratoRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Contratos", description = "Gestión de contratos: crear, consultar, actualizar y eliminar.")
@SecurityRequirement(name = OpenApiConfig.BEARER_AUTH)
public interface ContratoRestControllerDocs {

    @Operation(
            summary = "Crear contrato",
            description = "Registra un nuevo contrato asociado a un usuario existente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Contrato creado exitosamente.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ContratoRestResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario asociado no encontrado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ContratoRestResponse create(@Valid @RequestBody CreateContratoRestRequest request);

    @Operation(
            summary = "Listar todos los contratos",
            description = "Retorna la lista completa de contratos.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de contratos obtenida exitosamente.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ContratoRestResponse.class)))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    List<ContratoRestResponse> getAll();

    @Operation(
            summary = "Obtener contrato por ID",
            description = "Retorna los datos de un contrato específico identificado por su ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Contrato encontrado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ContratoRestResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe ningún contrato con el ID proporcionado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ContratoRestResponse getById(
            @Parameter(description = "ID numérico del contrato.", example = "1", required = true)
            @PathVariable
            Integer id);

    @Operation(
            summary = "Actualizar contrato",
            description = "Actualiza los datos de un contrato existente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Contrato actualizado exitosamente.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ContratoRestResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Contrato o usuario asociado no encontrado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    ContratoRestResponse update(
            @Parameter(description = "ID numérico del contrato a actualizar.", example = "1", required = true)
            @PathVariable
            Integer id,
            @Valid @RequestBody UpdatContratoRestRequest request);

    @Operation(
            summary = "Eliminar contrato",
            description = "Elimina permanentemente un contrato del sistema por su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contrato eliminado exitosamente."),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe ningún contrato con el ID proporcionado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    void delete(
            @Parameter(description = "ID numérico del contrato a eliminar.", example = "1", required = true)
            @PathVariable
            Integer id);
}