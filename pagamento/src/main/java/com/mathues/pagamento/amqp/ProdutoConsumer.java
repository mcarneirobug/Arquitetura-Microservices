package com.mathues.pagamento.amqp;

import com.mathues.pagamento.modules.produto.mapper.ProdutoMapper;
import com.mathues.pagamento.modules.produto.response.ProdutoResponse;
import com.mathues.pagamento.modules.produto.service.impl.ProdutoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConsumer {

    private final ProdutoRepository repository;

    public ProdutoConsumer(ProdutoRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void consumerProduto(@Payload ProdutoResponse produtoResponse) {
        repository.save(ProdutoMapper.to(produtoResponse));
    }

}
