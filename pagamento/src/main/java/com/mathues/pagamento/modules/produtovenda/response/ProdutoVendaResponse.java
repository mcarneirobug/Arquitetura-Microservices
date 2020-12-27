package com.mathues.pagamento.modules.produtovenda.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class ProdutoVendaResponse extends RepresentationModel<ProdutoVendaResponse> implements Serializable {

    private static final long serialVersionUID = -2710206751651598527L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("idProduto")
    private Long idProduto;

    @JsonProperty("quantidade")
    private Integer quantidade;

}
