package com.mathues.pagamento.modules.venda.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mathues.pagamento.model.ProdutoVenda;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "data", "produtos", "valorTotal"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = false)
public class VendaResponse extends RepresentationModel<VendaResponse> implements Serializable {

    private static final long serialVersionUID = -4079898947606914312L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("data")
//    @JsonDeserialize(using = MultiDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @JsonProperty("produtos")
    private List<ProdutoVenda> produtos;

    @JsonProperty("valorTotal")
    private Double valorTotal;

}
