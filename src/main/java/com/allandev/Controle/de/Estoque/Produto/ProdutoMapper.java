package com.allandev.Controle.de.Estoque.Produto;

import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoModel map(ProdutoDTO produtoDTO){
        ProdutoModel produtoModel = new ProdutoModel();

        produtoModel.setId(produtoDTO.getId());
        produtoModel.setNome(produtoDTO.getNome());
        produtoModel.setDescricao(produtoDTO.getDescricao());
        produtoModel.setPreco(produtoDTO.getPreco());
        produtoModel.setFornecedor(produtoDTO.getFornecedor());
        produtoModel.setEstoque(produtoDTO.getEstoque());

        return  produtoModel;


    }
}
