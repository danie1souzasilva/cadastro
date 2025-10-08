package com.funcionario.cadastro.repositorio;

import com.funcionario.cadastro.entidade.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
