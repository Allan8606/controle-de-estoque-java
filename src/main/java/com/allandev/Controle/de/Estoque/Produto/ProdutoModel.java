package com.allandev.Controle.de.Estoque.Produto;


import com.allandev.Controle.de.Estoque.Estoque.EstoqueModel;
import com.allandev.Controle.de.Estoque.Fornecedor.FornecedorModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private double preco;

    @ManyToOne// Muitos produtos podem ter um fornecedor
    @JoinColumn(name = "fornecedor_id") //Na tabela deste objeto, crie uma coluna para se juntar à tabela de outro objeto.
    FornecedorModel fornecedor; // Deve puxar do fornecedor

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    EstoqueModel estoque; //Deve puxar do estoque

}
