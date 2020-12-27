package com.mathues.pagamento.modules.produtovenda.mapper;

import com.mathues.pagamento.model.ProdutoVenda;
import com.mathues.pagamento.modules.produtovenda.request.ProdutoVendaRequest;
import com.mathues.pagamento.modules.produtovenda.response.ProdutoVendaResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoVendaMapper {

    public static ProdutoVenda to(ProdutoVendaRequest produtoVendaRequest) {

        final var produtoVenda = new ProdutoVenda();
        produtoVenda.setIdProduto(produtoVendaRequest.getIdProduto());
        produtoVenda.setQuantidade(produtoVendaRequest.getQuantidade());

        return produtoVenda;
    }

    public static ProdutoVendaResponse toResponse(ProdutoVenda produtoVenda) {

        final var produtoResponse = new ProdutoVendaResponse();
        produtoResponse.setId(produtoVenda.getId());
        produtoResponse.setIdProduto(produtoVenda.getIdProduto());
        produtoResponse.setQuantidade(produtoVenda.getQuantidade());

        return produtoResponse;
    }

    public static ProdutoVenda to(ProdutoVendaResponse produtoVendaResponse) {

        final var produtoVenda = new ProdutoVenda();
        produtoVenda.setId(produtoVendaResponse.getId());
        produtoVenda.setIdProduto(produtoVendaResponse.getIdProduto());
        produtoVenda.setQuantidade(produtoVendaResponse.getQuantidade());

        return produtoVenda;
    }

}
