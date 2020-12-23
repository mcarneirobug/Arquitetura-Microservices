package com.matheus.crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 7961230123741726361L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "estoque", nullable = false, length = 10)
    private Integer estoque;

    @Column(name = "preco", nullable = false, length = 10)
    private Double preco;
}
