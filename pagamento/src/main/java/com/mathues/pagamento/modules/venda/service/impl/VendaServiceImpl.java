package com.mathues.pagamento.modules.venda.service.impl;

import com.mathues.pagamento.exception.ResourceNotFoundException;
import com.mathues.pagamento.model.ProdutoVenda;
import com.mathues.pagamento.modules.produtovenda.mapper.ProdutoVendaMapper;
import com.mathues.pagamento.modules.produtovenda.service.impl.ProdutoVendaRepository;
import com.mathues.pagamento.modules.venda.mapper.VendaMapper;
import com.mathues.pagamento.modules.venda.request.VendaRequest;
import com.mathues.pagamento.modules.venda.response.VendaResponse;
import com.mathues.pagamento.modules.venda.service.VendaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository repository;

    private final ProdutoVendaRepository produtoVendaRepository;

    public VendaServiceImpl(VendaRepository repository, ProdutoVendaRepository produtoVendaRepository) {
        this.repository = repository;
        this.produtoVendaRepository = produtoVendaRepository;
    }

    @Override
    public VendaResponse create(VendaRequest vendaRequest) {
        final var venda = this.repository.save(VendaMapper.to(vendaRequest));

        List<ProdutoVenda> produtosSalvos = new ArrayList<>();

        venda.getProdutos().forEach(p -> {
            final var produto = ProdutoVendaMapper.toResponse(p);
            final var product = ProdutoVendaMapper.to(produto);
            product.setVenda(venda);
            produtosSalvos.add(produtoVendaRepository.save(product));
        });

        venda.setProdutos(produtosSalvos);

        return VendaMapper.toResponse(venda);
    }

    @Override
    public Page<VendaResponse> findAll(Pageable pageable) {
        var page = this.repository.findAll(pageable);
        return page.map(VendaMapper::toResponse);
    }

    @Override
    public VendaResponse findById(Long id) {
        return this.repository.findById(id)
                .map(VendaMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }
}
