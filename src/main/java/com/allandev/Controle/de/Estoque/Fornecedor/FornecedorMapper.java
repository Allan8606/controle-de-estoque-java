package com.allandev.Controle.de.Estoque.Fornecedor;


import org.springframework.stereotype.Component;

@Component
public class FornecedorMapper {

    public FornecedorModel map(FornecedorDTO fornecedorDTO){

        FornecedorModel fornecedorModel = new FornecedorModel();

        fornecedorModel.setId(fornecedorDTO.getId());
        fornecedorModel.setNome(fornecedorDTO.getNome());
        fornecedorModel.setLocal(fornecedorDTO.getLocal());
        fornecedorModel.setContato(fornecedorDTO.getContato());

        return fornecedorModel;
    }

    public FornecedorDTO map(FornecedorModel fornecedorModel){

        FornecedorDTO fornecedorDTO = new FornecedorDTO();

        fornecedorDTO.setId(fornecedorModel.getId());
        fornecedorDTO.setNome(fornecedorModel.getNome());
        fornecedorDTO.setLocal(fornecedorModel.getLocal());
        fornecedorDTO.setContato(fornecedorModel.getContato());

        return fornecedorDTO;
    }

}
