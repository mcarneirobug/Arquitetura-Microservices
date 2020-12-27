package com.mathues.pagamento.modules.venda.mapper;

import com.mathues.pagamento.model.Venda;
import com.mathues.pagamento.modules.venda.request.VendaRequest;
import com.mathues.pagamento.modules.venda.response.VendaResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VendaMapper {

    public static Venda to(VendaRequest vendaRequest) {

        final var venda = new Venda();
        venda.setData(vendaRequest.getData());
        venda.setProdutos(vendaRequest.getProdutos());
        venda.setValorTotal(vendaRequest.getValorTotal());

        return venda;
    }

    public static VendaResponse toResponse(Venda venda) {

        final var vendaResponse = new VendaResponse();
        vendaResponse.setId(venda.getId());
        vendaResponse.setData(venda.getData());
        vendaResponse.setProdutos(venda.getProdutos());
        vendaResponse.setValorTotal(venda.getValorTotal());

        return vendaResponse;
    }

    public static Venda to(VendaResponse vendaResponse) {

        final var venda = new Venda();
        venda.setId(vendaResponse.getId());
        venda.setValorTotal(vendaResponse.getValorTotal());
        venda.setProdutos(vendaResponse.getProdutos());
        venda.setData(vendaResponse.getData());

        return venda;
    }
}
