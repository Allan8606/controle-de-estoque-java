package com.allandev.Controle.de.Estoque.Fornecedor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private FornecedorMapper fornecedorMapper;


//    Adicionar um fornecedor: Salvar um novo fornecedor.
    public FornecedorDTO adicionarFornecedor(FornecedorDTO fornecedorDTO){
        FornecedorModel fornecedorModel = fornecedorMapper.map(fornecedorDTO);
        fornecedorModel = fornecedorRepository.save(fornecedorModel);
        return fornecedorMapper.map(fornecedorModel);
    }

//    Listar todos os fornecedores: Retornar uma lista de todos os fornecedores.
    public List<FornecedorDTO> listarFornecedores(){
        List<FornecedorModel> fornecedores = fornecedorRepository.findAll();
        return fornecedores.stream()
                .map(fornecedorMapper::map)
                .collect(Collectors.toList());
    }
//
//    Buscar um fornecedor por ID: Encontrar um fornecedor específico.
    public FornecedorDTO buscarForncedorPorId(Long id){
        Optional<FornecedorModel> fornecedor = fornecedorRepository.findById(id);
        return fornecedor.map(fornecedorMapper::map).orElse(null);
    }
//
//    Atualizar um fornecedor: Modificar as informações de um fornecedor.
    public FornecedorDTO atualizarFornecedor(Long id, FornecedorDTO fornecedorDTO){
        Optional<FornecedorModel> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()){
           FornecedorModel fornecedorModel = fornecedorMapper.map(fornecedorDTO);
           fornecedorModel.setId(id);
           fornecedorModel= fornecedorRepository.save(fornecedorModel);
           return  fornecedorMapper.map(fornecedorModel);
        }
        return null;
    }
//
//    Excluir um fornecedor: Remover um fornecedor do sistema.
    public void deletarFornecedor(Long id){
        Optional<FornecedorModel> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isEmpty()){
            fornecedorRepository.deleteById(id);
        }else {
            System.out.println("Fornecedor não encontrado");
        }
    }
}
