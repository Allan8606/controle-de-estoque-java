package com.allandev.Controle.de.Estoque.Estoque;


import com.allandev.Controle.de.Estoque.Produto.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueModel, Long> {
    Optional<EstoqueModel> findByProduto(ProdutoModel produto);
}
