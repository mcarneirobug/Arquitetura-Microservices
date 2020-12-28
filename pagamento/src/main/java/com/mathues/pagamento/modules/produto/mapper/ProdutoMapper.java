package com.mathues.pagamento.modules.produto.mapper;

import com.mathues.pagamento.model.Produto;
import com.mathues.pagamento.modules.produto.request.ProdutoRequest;
import com.mathues.pagamento.modules.produto.response.ProdutoResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoMapper {

    public static Produto to(ProdutoRequest produtoRequest) {

        final var produto = new Produto();
        produto.setEstoque(produtoRequest.getEstoque());

        return produto;
    }

    public static ProdutoResponse toResponse(Produto produto) {

        final var produtoResponse = new ProdutoResponse();
        produtoResponse.setId(produto.getId());
        produtoResponse.setEstoque(produto.getEstoque());

        return produtoResponse;
    }

    public static Produto to(ProdutoResponse produtoResponse) {

        final var produto = new Produto();

        produto.setId(produtoResponse.getId());
        produto.setEstoque(produtoResponse.getEstoque());

        return produto;
    }
}
