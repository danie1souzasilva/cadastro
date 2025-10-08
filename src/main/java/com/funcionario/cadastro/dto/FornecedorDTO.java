package com.funcionario.cadastro.dto;

import com.funcionario.cadastro.entidade.Produto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record FornecedorDTO(
        Long id,
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}")String telefone,
        @NotBlank String cnpj,
        Produto produto
        ) {
}
