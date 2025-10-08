package com.funcionario.cadastro.entidade;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

}
