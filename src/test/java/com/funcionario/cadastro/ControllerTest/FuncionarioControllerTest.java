package com.funcionario.cadastro.ControllerTest;

import com.funcionario.cadastro.entidade.Funcionario;
import com.funcionario.cadastro.enums.Cargo;
import com.funcionario.cadastro.repositorio.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    void setup() {funcionarioRepository.deleteAll();
    }
    @Test
    void deveDeletarFuncionarioExistente() throws Exception {
        // Cria e salva um funcionário
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Daniel");
        funcionario.setCpf("123.456.789-00");
        funcionario.setEmail("daniel@email.com");
        funcionario.setTelefone("(61) 99999-9999");
        funcionario.setSenha("senha123");
        funcionario.setSalario(BigDecimal.valueOf(3000));
        funcionario.setCargo(Cargo.PROGRAMADOR);

        Funcionario salvo = funcionarioRepository.save(funcionario);

        mockMvc.perform(delete("/funcionarios/" + salvo.getId()))
                .andExpect(status().isNoContent());
    }
}

