package com.funcionario.cadastro.dto;

import com.funcionario.cadastro.enums.Cargo;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record FuncDTO(
        Long id,
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}") String telefone,
        @NotBlank @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}") String cpf,
        @NotNull @DecimalMin(value = "1620.00", inclusive = false) @DecimalMax(value = "43000.00", inclusive = false) BigDecimal salario,
        Cargo cargo,
        String senha
) {}

