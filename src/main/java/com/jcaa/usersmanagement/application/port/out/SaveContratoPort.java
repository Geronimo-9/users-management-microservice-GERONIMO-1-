package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Contrato;

public interface SaveContratoPort {
  Contrato save(Contrato contrato);
}