package com.matheus.crud.modules.produto.service.impl;

import com.matheus.crud.exception.ResourceNotFoundException;
import com.matheus.crud.model.Produto;
import com.matheus.crud.modules.produto.mapper.ProdutoMapper;
import com.matheus.crud.modules.produto.request.ProdutoRequest;
import com.matheus.crud.modules.produto.response.ProdutoResponse;
import com.matheus.crud.modules.produto.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoServiceImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProdutoResponse create(ProdutoRequest produtoRequest) {
        return ProdutoMapper
                .toResponse(this.repository.save(ProdutoMapper.of(produtoRequest)));
    }

    @Override
    public Page<ProdutoResponse> findAll(Pageable pageable) {
        var page = this.repository.findAll(pageable);
        return page.map(ProdutoMapper::toResponse);
    }

    @Override
    public Produto findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    @Override
    public ProdutoResponse update(ProdutoRequest produtoRequest, Long id) {

        final var produto = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        produto.setNome(produtoRequest.getNome());
        produto.setEstoque(produtoRequest.getEstoque());
        produto.setPreco(produtoRequest.getPreco());

        return ProdutoMapper.toResponse(this.repository.save(produto));
    }

    @Override
    public void delete(Long id) {
       final var produto = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        this.repository.delete(produto);
    }
}
