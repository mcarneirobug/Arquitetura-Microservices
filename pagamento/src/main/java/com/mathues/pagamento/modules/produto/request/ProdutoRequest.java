package com.mathues.pagamento.modules.produto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoRequest implements Serializable {

    private static final long serialVersionUID = -3066411979106588438L;

    @JsonProperty("estoque")
    private Integer estoque;

}
