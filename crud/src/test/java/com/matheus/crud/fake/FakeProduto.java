package com.matheus.crud.fake;

import com.matheus.crud.model.Produto;
import com.matheus.crud.modules.produto.request.ProdutoRequest;
import com.matheus.crud.modules.produto.response.ProdutoResponse;

public class FakeProduto {

    public static Produto generate() {
        final var produto = new Produto();
        produto.setId(1L);
        produto.setNome("Mac book Pro");
        produto.setEstoque(1);
        produto.setPreco(2000.0);

        return produto;
    }

    public static ProdutoResponse generateProdutoResponse() {
        final var produtoResponse = new ProdutoResponse();
        produtoResponse.setId(1L);
        produtoResponse.setNome("Mac book Pro");
        produtoResponse.setEstoque(1);
        produtoResponse.setPreco(2000.0);

        return produtoResponse;
    }

    public static ProdutoRequest generateProdutoRequest() {
        final var produtoRequest = new ProdutoRequest();
        produtoRequest.setNome("Mac book Pro");
        produtoRequest.setEstoque(1);
        produtoRequest.setPreco(2000.0);

        return produtoRequest;
    }
}
