package com.allandev.Controle.de.Estoque.Estoque;

import com.allandev.Controle.de.Estoque.Produto.ProdutoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueDTO
{

    private Long id;
    ProdutoModel produto;

    private int quantidade;
    private boolean disponivel;
    private String localizacao;
}
