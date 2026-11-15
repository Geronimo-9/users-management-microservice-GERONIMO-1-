package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.Estado;
import com.jcaa.usersmanagement.domain.enums.FrecuenciaPago;
import com.jcaa.usersmanagement.domain.exception.FechaInvalidaExcepcion;
import com.jcaa.usersmanagement.domain.exception.ValoresNulosExcepcion;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import com.jcaa.usersmanagement.domain.valueobject.contrato.Empleado;
import com.jcaa.usersmanagement.domain.valueobject.contrato.Empresa;
import com.jcaa.usersmanagement.domain.valueobject.contrato.FechaFin;
import com.jcaa.usersmanagement.domain.valueobject.contrato.FechaFirma;
import com.jcaa.usersmanagement.domain.valueobject.contrato.FechaInicio;
import com.jcaa.usersmanagement.domain.valueobject.contrato.Funciones;
import com.jcaa.usersmanagement.domain.valueobject.contrato.Monto;
import java.time.LocalDate;
import lombok.Value;

@Value
public class Contrato {

    Integer id; // Auto-incrementable en BD (null antes de persistir)
    UserId idUsuario;
    Empresa empresa;
    Empleado empleado;
    Funciones funciones;
    Monto monto;
    FrecuenciaPago frecuenciaPago;
    FechaFirma fechaFirma;
    FechaInicio fechaInicio;
    FechaFin fechaFin;
    Estado estado;

    public Contrato(
            final Integer id,
            final UserId idUsuario,
            final Empresa empresa,
            final Empleado empleado,
            final Funciones funciones,
            final Monto monto,
            final FrecuenciaPago frecuenciaPago,
            final FechaFirma fechaFirma,
            final FechaInicio fechaInicio,
            final FechaFin fechaFin,
            final Estado estado) {
        this.id = id;
        this.idUsuario = requireNonNull(idUsuario, "idUsuario");
        this.empresa = requireNonNull(empresa, "empresa");
        this.empleado = requireNonNull(empleado, "empleado");
        this.funciones = requireNonNull(funciones, "funciones");
        this.monto = requireNonNull(monto, "monto");
        this.frecuenciaPago = requireNonNull(frecuenciaPago, "frecuenciaPago");
        this.fechaFirma = requireNonNull(fechaFirma, "fechaFirma");
        this.fechaInicio = requireNonNull(fechaInicio, "fechaInicio");
        this.fechaFin = requireNonNull(fechaFin, "fechaFin");
        this.estado = requireNonNull(estado, "estado");

        validarPlazos(fechaFirma, fechaInicio, fechaFin);
    }

    public static Contrato crear(
            final UserId idUsuario,
            final Empresa empresa,
            final Empleado empleado,
            final Funciones funciones,
            final Monto monto,
            final FrecuenciaPago frecuenciaPago,
            final FechaFirma fechaFirma,
            final FechaInicio fechaInicio,
            final FechaFin fechaFin) {
        final Estado estadoInicial =
                fechaFin.value().isBefore(LocalDate.now()) ? Estado.VENCIDO : Estado.VIGENTE;
        return new Contrato(
                null,
                idUsuario,
                empresa,
                empleado,
                funciones,
                monto,
                frecuenciaPago,
                fechaFirma,
                fechaInicio,
                fechaFin,
                estadoInicial);
    }

    public Contrato marcarComoVencido() {
        return new Contrato(
                id,
                idUsuario,
                empresa,
                empleado,
                funciones,
                monto,
                frecuenciaPago,
                fechaFirma,
                fechaInicio,
                fechaFin,
                Estado.VENCIDO);
    }

    public Contrato marcarComoVigente() {
        return new Contrato(
                id,
                idUsuario,
                empresa,
                empleado,
                funciones,
                monto,
                frecuenciaPago,
                fechaFirma,
                fechaInicio,
                fechaFin,
                Estado.VIGENTE);
    }

    private static void validarPlazos(
            final FechaFirma fechaFirma, final FechaInicio fechaInicio, final FechaFin fechaFin) {
        if (fechaFin.isBefore(fechaInicio)) {
            throw FechaInvalidaExcepcion.fechaInconsistente();
        }
        if (fechaFirma.value().isAfter(fechaFin.value())) {
            throw FechaInvalidaExcepcion.firmaPosteriorAFin();
        }
    }

    private static <T> T requireNonNull(final T value, final String fieldName) {
        if (value == null) {
            throw ValoresNulosExcepcion.parametroNulo(fieldName);
        }
        return value;
    }
}