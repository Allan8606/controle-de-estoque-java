package com.allan_dev.demo.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produto")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Long produtoId;

    private String nome;

    private int quantidade;

    private double preco;
}
