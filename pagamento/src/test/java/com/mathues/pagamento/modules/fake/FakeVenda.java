package com.mathues.pagamento.modules.fake;

import com.mathues.pagamento.model.Venda;
import com.mathues.pagamento.modules.venda.request.VendaRequest;
import com.mathues.pagamento.modules.venda.response.VendaResponse;

import java.time.LocalDate;
import java.util.List;

public class FakeVenda {

    public static Venda generate() {

        final var venda = new Venda();

        venda.setId(1L);
        venda.setData(LocalDate.now());
        venda.setValorTotal(0.0);
        venda.setProdutos(List.of(FakeProdutoVenda.generate()));

        return venda;
    }

    public static VendaResponse generateVendaResponse() {

        final var vendaResponse = new VendaResponse();

        vendaResponse.setId(1L);
        vendaResponse.setData(LocalDate.now());
        vendaResponse.setValorTotal(0.0);
        vendaResponse.setProdutos(List.of(FakeProdutoVenda.generate()));

        return vendaResponse;
    }

    public static VendaRequest generateVendaRequest() {

        final var vendaRequest = new VendaRequest();

        vendaRequest.setData(LocalDate.now());
        vendaRequest.setValorTotal(0.0);
        vendaRequest.setProdutos(List.of(FakeProdutoVenda.generate()));

        return vendaRequest;
    }
}
