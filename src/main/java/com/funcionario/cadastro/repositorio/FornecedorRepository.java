package com.funcionario.cadastro.repositorio;

import com.funcionario.cadastro.entidade.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Fornecedor findByEmail(String email);
}
