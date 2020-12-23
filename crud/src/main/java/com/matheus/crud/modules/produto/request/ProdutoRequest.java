package com.matheus.crud.modules.produto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonPropertyOrder({"nome", "estoque", "preco"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class ProdutoRequest implements Serializable {

    private static final long serialVersionUID = 4637779111362846726L;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("estoque")
    private Integer estoque;

    @JsonProperty("preco")
    private Double preco;
}
