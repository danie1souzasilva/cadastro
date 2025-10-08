package com.funcionario.cadastro.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cnpj;
    @NotBlank
    private String email;
    @NotBlank
    private  String telefone;
    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();
}
