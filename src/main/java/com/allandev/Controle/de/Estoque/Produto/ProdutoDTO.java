package com.allandev.Controle.de.Estoque.Produto;


import com.allandev.Controle.de.Estoque.Estoque.EstoqueModel;
import com.allandev.Controle.de.Estoque.Fornecedor.FornecedorModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private double preco;

    FornecedorModel fornecedor;
    EstoqueModel estoque;
}
