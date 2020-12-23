package com.matheus.crud.modules.produto.service;

import com.matheus.crud.modules.produto.request.ProdutoRequest;
import com.matheus.crud.modules.produto.response.ProdutoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {

    ProdutoResponse create(ProdutoRequest produtoRequest);

    Page<ProdutoResponse> findAll(Pageable pageable);

    ProdutoResponse findById(Long id);

    ProdutoResponse update(ProdutoRequest produtoRequest, Long id);

    void delete(Long id;)
}
