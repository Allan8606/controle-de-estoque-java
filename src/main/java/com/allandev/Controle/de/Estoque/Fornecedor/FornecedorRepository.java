package com.allandev.Controle.de.Estoque.Fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorModel , Long> {
}
