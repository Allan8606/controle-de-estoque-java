package com.allandev.Controle.de.Estoque.Fornecedor;


import org.springframework.stereotype.Component;

@Component
public class FornecedorMapper {

    public FornecedorModel mpa(FornecedorDTO fornecedorDTO){

        FornecedorModel fornecedorModel = new FornecedorModel();

        fornecedorModel.setId(fornecedorDTO.getId());
        fornecedorModel.setNome(fornecedorDTO.getNome());
        fornecedorModel.setLocal(fornecedorDTO.getLocal());
        fornecedorModel.setContato(fornecedorDTO.getContato());

        return fornecedorModel;


    }

}
