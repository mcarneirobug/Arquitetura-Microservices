package com.matheus.crud.modules.produto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.matheus.crud.modules.produto.request.ProdutoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoResponse extends ProdutoRequest {

    @JsonProperty("id")
    private Long id;

}
