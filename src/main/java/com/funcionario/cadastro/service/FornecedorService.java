package com.funcionario.cadastro.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funcionario.cadastro.dto.FornecedorDTO;
import com.funcionario.cadastro.entidade.Fornecedor;
import com.funcionario.cadastro.mapper.MapearDTO;
import com.funcionario.cadastro.produtorKafka.ProdutorKafka;
import com.funcionario.cadastro.repositorio.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorService {
    private FornecedorRepository fornecedorRepositorio;
    private MapearDTO mapearDTO;
    private final ProdutorKafka produtorKafka;
    private final ObjectMapper objectMapper;

    public FornecedorService(ProdutorKafka produtorKafka, ObjectMapper objectMapper, MapearDTO mapearDTO, FornecedorRepository fornecedorRepositorio) {
        this.produtorKafka = produtorKafka;
        this.objectMapper = objectMapper;
        this.mapearDTO = mapearDTO;
        this.fornecedorRepositorio = fornecedorRepositorio;
    }
    public FornecedorDTO cadastrar(FornecedorDTO fornecedorDTO){
        Fornecedor entidade = mapearDTO.dtoParaFornecedor(fornecedorDTO);
        Fornecedor salvar = fornecedorRepositorio.save(entidade);
        FornecedorDTO dtoSalvo = mapearDTO.fornecedorParaDto(salvar);
        try {
            String mensagemFornecedor = objectMapper.writeValueAsString(fornecedorDTO);
            produtorKafka.enviarMensagemFornecedor(mensagemFornecedor);
        }catch (Exception e){
            System.err.println("❌ Erro ao enviar FornecedorDTO para Kafka:");
            e.printStackTrace();
        }
        return mapearDTO.fornecedorParaDto(salvar);
    }
    public List<FornecedorDTO> listarFornecedores(){
        List<Fornecedor> fornecedores = fornecedorRepositorio.findAll();
        return fornecedores.stream()
                .map(mapearDTO :: fornecedorParaDto).collect(Collectors.toList());
    }
    public void deletarFornecedor(Long id){
        if (!fornecedorRepositorio.existsById(id)){
            throw new EntityNotFoundException("fornecedor não existe");
        }
        fornecedorRepositorio.deleteById(id);
    }
    public FornecedorDTO atualizarFornecedor(Long id, FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = fornecedorRepositorio.findById(id).orElseThrow(() -> new RuntimeException("fornecedor não encontrado"));

        mapearDTO.atualizarFornecedor(fornecedorDTO, fornecedor);
        fornecedorRepositorio.save(fornecedor);
        return mapearDTO.fornecedorParaDto(fornecedor);
    }
}
