package com.matheus.crud.amqp;

import com.matheus.crud.modules.produto.mapper.ProdutoMapper;
import com.matheus.crud.modules.produto.response.ProdutoResponse;
import com.matheus.crud.modules.produto.service.impl.ProdutoRepository;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConsumer {

    private final ProdutoRepository repository;

    public ProdutoConsumer(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void consumerProduto(@Payload ProdutoResponse produtoResponse) {
        repository.save(ProdutoMapper.to(produtoResponse));
    }

}
