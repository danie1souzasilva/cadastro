package com.funcionario.cadastro.repositorio;

import com.funcionario.cadastro.entidade.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findByEmail(String email);
    List<Funcionario> findBySalarioGreaterThan(BigDecimal salarioMinimo);

}
