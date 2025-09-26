package com.funcionario.cadastro.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funcionario.cadastro.dto.FuncDTO;
import com.funcionario.cadastro.entidade.Funcionario;
import com.funcionario.cadastro.mapper.MapearDTO;
import com.funcionario.cadastro.produtorKafka.ProdutorKafka;
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

    private final ProdutorKafka produtorKafka;
    private final ObjectMapper objectMapper;

    public FuncionarioService(ProdutorKafka produtorKafka, ObjectMapper objectMapper) {
        this.produtorKafka = produtorKafka;
        this.objectMapper = objectMapper;
    }

    public FuncDTO cadastrar(FuncDTO funcDTO){
        Funcionario entidade = mapearDTO.dtoParaEntidade(funcDTO);
        Funcionario salvar = funcionarioRepository.save(entidade);
        try{
            String mensagemJson = objectMapper.writeValueAsString(funcDTO);
            produtorKafka.enviarMensagemFunci(mensagemJson);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapearDTO.entidadeParaDTO(salvar);
    }
    public List<FuncDTO>listarFunc(){
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(mapearDTO::entidadeParaDTO).collect(Collectors.toList());
    }

}
