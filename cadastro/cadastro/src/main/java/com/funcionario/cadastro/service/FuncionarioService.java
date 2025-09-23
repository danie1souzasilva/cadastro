package com.funcionario.cadastro.service;

import com.funcionario.cadastro.dto.FuncDTO;
import com.funcionario.cadastro.entidade.Funcionario;
import com.funcionario.cadastro.mapper.MapearDTO;
import com.funcionario.cadastro.repositorio.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private MapearDTO mapearDTO;

    public FuncDTO cadastrar(FuncDTO funcDTO){

        Funcionario entidade = mapearDTO.dtoParaEntidade(funcDTO);
        Funcionario salvar = funcionarioRepository.save(entidade);
        return mapearDTO.entidadeParaDTO(salvar);
    }
    public List<FuncDTO>listarFunc(){
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(mapearDTO::entidadeParaDTO).collect(Collectors.toList());
    }
}
