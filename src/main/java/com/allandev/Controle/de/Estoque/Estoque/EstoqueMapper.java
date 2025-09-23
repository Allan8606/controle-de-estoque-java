package com.allandev.Controle.de.Estoque.Estoque;


import org.springframework.stereotype.Component;

@Component
public class EstoqueMapper {

    public EstoqueModel map(EstoqueDTO estoqueDTO){
        EstoqueModel estoqueModel = new EstoqueModel();

        estoqueModel.setId(estoqueDTO.getId());
        estoqueModel.setProduto(estoqueDTO.getProduto());
        estoqueModel.setQuantidade(estoqueDTO.getQuantidade());
        estoqueModel.setDisponivel(estoqueDTO.isDisponivel());
        estoqueModel.setLocalizacao(estoqueDTO.getLocalizacao());

        return estoqueModel;
    }

    public EstoqueDTO map(EstoqueModel estoqueModel){
        EstoqueDTO estoqueDTO = new EstoqueDTO();

        estoqueDTO.setId(estoqueModel.getId());
        estoqueDTO.setProduto(estoqueModel.getProduto());
        estoqueDTO.setQuantidade(estoqueModel.getQuantidade());
        estoqueDTO.setDisponivel(estoqueModel.isDisponivel());
        estoqueDTO.setLocalizacao(estoqueModel.getLocalizacao());

        return estoqueDTO;
    }
}
