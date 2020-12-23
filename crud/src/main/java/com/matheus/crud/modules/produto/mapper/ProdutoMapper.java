package com.matheus.crud.modules.produto.mapper;

import com.matheus.crud.model.Produto;
import com.matheus.crud.modules.produto.request.ProdutoRequest;
import com.matheus.crud.modules.produto.response.ProdutoResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoMapper {

    public static Produto of(ProdutoRequest produtoRequest) {

        final var produto = new Produto();
        produto.setNome(produtoRequest.getNome());
        produto.setEstoque(produtoRequest.getEstoque());
        produto.setPreco(produtoRequest.getPreco());

        return produto;
    }

    public static ProdutoResponse toResponse(Produto produto) {

        final var produtoResponse = new ProdutoResponse();
        produtoResponse.setId(produto.getId());
        produtoResponse.setNome(produto.getNome());
        produtoResponse.setEstoque(produto.getEstoque());
        produtoResponse.setPreco(produto.getPreco());

        return produtoResponse;
    }
}
