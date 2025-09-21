package com.allandev.Controle.de.Estoque.Fornecedor;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {

    private Long id;
    private String nome;
    private String local;
    private String contato;
}
