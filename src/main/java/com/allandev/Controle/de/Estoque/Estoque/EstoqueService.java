package com.allandev.Controle.de.Estoque.Estoque;


import com.allandev.Controle.de.Estoque.Produto.ProdutoDTO;
import com.allandev.Controle.de.Estoque.Produto.ProdutoMapper;
import com.allandev.Controle.de.Estoque.Produto.ProdutoModel;
import com.allandev.Controle.de.Estoque.Produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private EstoqueMapper estoqueMapper;

    @Autowired
    private ProdutoMapper produtoMapper;

//    Dar entrada em produto: Adicionar uma quantidade de um produto específico no estoque.
    //Receebe um produto que deve ser adicionado e a quantidade a ser adicionada
    public EstoqueDTO adicionaQuantidade(ProdutoDTO produtoDTO, int quantidade){
        ProdutoModel produto = produtoMapper.map(produtoDTO);






    }




//    Dar saída em produto: Subtrair uma quantidade de um produto quando ele é vendido ou sai do estoque.
//
//    Verificar a quantidade disponível: Retornar a quantidade atual de um produto.
//
//    Ajustar estoque: Corrigir a quantidade de um produto em caso de erro ou avaria.
//
//    Consultar estoque por cidade: Buscar a quantidade de um produto em um determinado local.





}
