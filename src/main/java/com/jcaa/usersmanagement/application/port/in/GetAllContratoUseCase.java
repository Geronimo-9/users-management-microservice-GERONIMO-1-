package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Contrato;
import java.util.List;

public interface GetAllContratoUseCase {
  List<Contrato> execute();
}