package com.allan_dev.demo.repository;

import com.allan_dev.demo.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
