package com.funcionario.cadastro.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funcionario.cadastro.dto.ProdutoDTO;
import com.funcionario.cadastro.entidade.Produto;
import com.funcionario.cadastro.mapper.MapearDTO;
import com.funcionario.cadastro.produtorKafka.ProdutorKafka;
import com.funcionario.cadastro.repositorio.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;
    private MapearDTO mapearDTO;
    private final ProdutorKafka produtorKafka;
    private final ObjectMapper objectMapper;

    public ProdutoService(ProdutorKafka produtorKafka, ObjectMapper objectMapper, MapearDTO mapearDTO, ProdutoRepository produtoRepository) {
        this.produtorKafka = produtorKafka;
        this.objectMapper = objectMapper;
        this.mapearDTO = mapearDTO;
        this.produtoRepository = produtoRepository;
    }
    public ProdutoDTO cadastrar(ProdutoDTO produtoDTO){
        Produto entidade = mapearDTO.dtoParaProduto(produtoDTO);
        Produto salvar = produtoRepository.save(entidade);
        ProdutoDTO dtoSalvo = mapearDTO.produtoParaDTO(salvar);
        try {
            String mensagemProduto = objectMapper.writeValueAsString(produtoDTO);
            produtorKafka.enviarMensagemProduto(mensagemProduto);
        }catch (Exception e){
            System.err.println("❌ Erro ao enviar ProdutoDTO para Kafka:");
            e.printStackTrace();
        }
        return mapearDTO.produtoParaDTO(salvar);
    }
}
