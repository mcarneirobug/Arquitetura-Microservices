package com.mathues.pagamento.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Entity
@Table(catalog = "pagamento", name = "venda")
public class Venda implements Serializable {

    private static final long serialVersionUID = 3217135588465193576L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.REFRESH)
    private List<ProdutoVenda> produtos;

    @Column(name = "valor_total", nullable = false, length = 10)
    private Double valorTotal;
}
