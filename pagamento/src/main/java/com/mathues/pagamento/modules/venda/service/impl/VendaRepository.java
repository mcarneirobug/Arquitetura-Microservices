package com.mathues.pagamento.modules.venda.service.impl;

import com.mathues.pagamento.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
