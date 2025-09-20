package com.allandev.Controle.de.Estoque.Estoque;


import com.allandev.Controle.de.Estoque.Produto.ProdutoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_estoque")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    ProdutoModel produto;// Deve puxar do produto


    private int quantidade;
    private boolean disponivel;
    private String localizacao;


}