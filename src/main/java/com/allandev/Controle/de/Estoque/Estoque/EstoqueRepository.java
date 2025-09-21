package com.allandev.Controle.de.Estoque.Estoque;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueModel, Long> {
}
