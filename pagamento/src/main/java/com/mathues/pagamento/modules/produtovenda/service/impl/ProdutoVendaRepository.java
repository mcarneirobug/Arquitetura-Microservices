package com.mathues.pagamento.modules.produtovenda.service.impl;

import com.mathues.pagamento.model.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {
}
