package com.mathues.pagamento.modules.venda.service;

import com.mathues.pagamento.modules.venda.request.VendaRequest;
import com.mathues.pagamento.modules.venda.response.VendaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VendaService {

    VendaResponse create(VendaRequest vendaRequest);

    Page<VendaResponse> findAll(Pageable pageable);

    VendaResponse findById(Long id);

}
