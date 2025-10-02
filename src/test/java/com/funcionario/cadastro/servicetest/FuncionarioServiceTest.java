package com.funcionario.cadastro.servicetest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funcionario.cadastro.mapper.MapearDTO;
import com.funcionario.cadastro.produtorKafka.ProdutorKafka;
import com.funcionario.cadastro.repositorio.FuncionarioRepository;
import com.funcionario.cadastro.service.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private MapearDTO mapearDTO;

    @Mock
    private ProdutorKafka produtorKafka;

    @Mock
    private ObjectMapper objectMapper;


    @InjectMocks
    private FuncionarioService funcionarioService;

    @Test
    void  deveDeletarFuncionario(){
        Long id = 1L;
        when(funcionarioRepository.existsById(id)).thenReturn(true);

        funcionarioService.deletarPorId(id);

        verify(funcionarioRepository).deleteById(id);
    }
    @Test
    void deveLancarExceptionQuandoNaoExisteFuncionario(){
        Long id = 99L;

        when(funcionarioRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {funcionarioService.deletarPorId(id);});
    }
}
