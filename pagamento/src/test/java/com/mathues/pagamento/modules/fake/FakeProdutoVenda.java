package com.mathues.pagamento.modules.fake;

import com.mathues.pagamento.model.ProdutoVenda;

public class FakeProdutoVenda {

    public static ProdutoVenda generate() {

        final var produtoVenda = new ProdutoVenda();
        produtoVenda.setId(1L);
        produtoVenda.setIdProduto(1L);
        produtoVenda.setQuantidade(1);
//        produtoVenda.setVenda(FakeVenda.generate());

        return produtoVenda;
    }
}
