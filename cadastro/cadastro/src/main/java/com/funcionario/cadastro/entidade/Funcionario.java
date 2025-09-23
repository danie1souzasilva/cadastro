package com.funcionario.cadastro.entidade;

import com.funcionario.cadastro.enums.Cargo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "funcionario")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank(message = "o email é obrigatorio")
    @Email(message = "E-mail invalido")
    private String email;
    @NotBlank
    @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}", message = "numero deve ter o formato: (00) 00000-0000 ou (00) 0000-0000")
    private String telefone;
    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "cpf deve ter o formato: 000.000.000-00")
    private String cpf;
    @NotNull(message = "o cara precisa de salario né")
    @DecimalMin(value = "1620.00", inclusive = false, message = "Osalario deve ser maior que 1619.00")
    @DecimalMax(value = "43000.00",inclusive = false, message = "o salario não pode passar de 43000.00")
    private BigDecimal salario;
    private Cargo cargo;

}
