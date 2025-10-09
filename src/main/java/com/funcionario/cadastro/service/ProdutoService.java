package com.funcionario.cadastro.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funcionario.cadastro.dto.ProdutoDTO;
import com.funcionario.cadastro.entidade.Produto;
import com.funcionario.cadastro.mapper.MapearDTO;
import com.funcionario.cadastro.produtorKafka.ProdutorKafka;
import com.funcionario.cadastro.repositorio.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProdutoDTO> listarTodos() {
        List<Produto> produto = produtoRepository.findAll();
        return produto.stream().map(mapearDTO::produtoParaDTO).collect(Collectors.toList());
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

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
    public ProdutoDTO atualizar(Long id, ProdutoDTO produtoDTO) {
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        mapearDTO.atualizarProduto(produtoDTO, produtoExistente);
        Produto atualizado = produtoRepository.save(produtoExistente);
        ProdutoDTO dtoAtualizado = mapearDTO.produtoParaDTO(atualizado);

        try {
            String mensagemProduto = objectMapper.writeValueAsString(dtoAtualizado);
            produtorKafka.enviarMensagemProduto(mensagemProduto);
        } catch (Exception e) {
            System.err.println("❌ Erro ao enviar ProdutoDTO atualizado para Kafka:");
            e.printStackTrace();
        }
        return dtoAtualizado;
    }
}
