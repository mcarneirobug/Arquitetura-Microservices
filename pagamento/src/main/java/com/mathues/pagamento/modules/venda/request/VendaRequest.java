package com.mathues.pagamento.modules.venda.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mathues.pagamento.model.ProdutoVenda;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VendaRequest implements Serializable {

    private static final long serialVersionUID = 7690482421595073636L;

    @JsonProperty("data")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data = LocalDate.now();

    @JsonProperty("produtos")
    private List<ProdutoVenda> produtos;

    @JsonProperty("valorTotal")
    private Double valorTotal;

}
