package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllContratoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllContratoPort;
import com.jcaa.usersmanagement.domain.model.Contrato;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllContratoService implements GetAllContratoUseCase {

  private final GetAllContratoPort getAllContratoPort;

  @Override
  public List<Contrato> execute() {
    return getAllContratoPort.getAll();
  }
}