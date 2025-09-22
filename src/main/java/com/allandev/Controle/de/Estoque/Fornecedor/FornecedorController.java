package com.allandev.Controle.de.Estoque.Fornecedor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

//    Adicionar um fornecedor: Salvar um novo fornecedor.
    @PostMapping("/cadastrar")
    public ResponseEntity<String> adicionarFornecedor(@RequestBody FornecedorDTO fornecedorDTO){
        FornecedorDTO fornecedor =  fornecedorService.adicionarFornecedor(fornecedorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(("Fornecedor " + fornecedor.getNome() + " cadastrado com sucesso"));
    }

//    Listar todos os fornecedores: Retornar uma lista de todos os fornecedores.
    @GetMapping("/listar")
    public ResponseEntity<List<FornecedorDTO>> listarFornecedores(){
        List<FornecedorDTO> fornecedores = fornecedorService.listarFornecedores();
        return ResponseEntity.ok(fornecedores);
    }

//    Buscar um fornecedor por ID: Encontrar um fornecedor específico.
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarForncedorPorId(@PathVariable Long id){
       FornecedorDTO forncedor = fornecedorService.buscarForncedorPorId(id);

       if(forncedor != null){
           return ResponseEntity.ok(forncedor);
       }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado");
    }

//    Atualizar um fornecedor: Modificar as informações de um fornecedor.
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarFornecedor(@PathVariable Long id, @RequestBody FornecedorDTO forncedorDTO){
        FornecedorDTO fornecedor = fornecedorService.atualizarFornecedor(id, forncedorDTO);

        if (fornecedor != null){
            return ResponseEntity.ok(fornecedor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado");
    }

//    Excluir um fornecedor: Remover um fornecedor do sistema.
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarFornecedor(@PathVariable Long id){
        if (fornecedorService.buscarForncedorPorId(id) != null){
            fornecedorService.deletarFornecedor(id);
            return ResponseEntity.ok("Fornecedor deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado");
    }


}
