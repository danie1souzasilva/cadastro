package com.funcionario.cadastro.consumidorKafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funcionario.cadastro.dto.FuncDTO;
import com.funcionario.cadastro.dto.ProdutoDTO;
import com.funcionario.cadastro.dto.FornecedorDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConsumidorKafka {

    private final ObjectMapper objectMapper;
    private final Validator validator;

    public ConsumidorKafka(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @KafkaListener(topics = "funcionarios.criados", groupId = "grupo-funcionarios")
    public void consumirFuncionario(ConsumerRecord<String, String> registro) {
        String mensagemJson = registro.value();

        try {
            FuncDTO funcDTO = objectMapper.readValue(mensagemJson, FuncDTO.class);
            Set<ConstraintViolation<FuncDTO>> violacoes = validator.validate(funcDTO);
            if (!violacoes.isEmpty()) {
                System.out.println("⚠️ Violação de validação recebida do Kafka (Funcionario):");
                violacoes.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));
                return;
            }

            System.out.println("✅ FuncDTO recebido e válido:");
            System.out.println(funcDTO);

        } catch (Exception e) {
            System.err.println("❌ Erro ao desserializar ou validar FuncDTO:");
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "produtos.criados", groupId = "grupo-produtos")
    public void consumirProduto(ConsumerRecord<String, String> registro) {
        String mensagemJson = registro.value();

        try {
            ProdutoDTO produtoDTO = objectMapper.readValue(mensagemJson, ProdutoDTO.class);
            Set<ConstraintViolation<ProdutoDTO>> violacoes = validator.validate(produtoDTO);
            if (!violacoes.isEmpty()) {
                System.out.println("⚠️ Violação de validação recebida do Kafka (Produto):");
                violacoes.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));
                return;
            }

            System.out.println("✅ ProdutoDTO recebido e válido:");
            System.out.println(produtoDTO);

        } catch (Exception e) {
            System.err.println("❌ Erro ao desserializar ou validar ProdutoDTO:");
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "fornecedores.criados", groupId = "grupo-fornecedores")
    public void consumirFornecedor(ConsumerRecord<String, String> registro) {
        String mensagemJson = registro.value();

        try {
            FornecedorDTO fornecedorDTO = objectMapper.readValue(mensagemJson, FornecedorDTO.class);
            Set<ConstraintViolation<FornecedorDTO>> violacoes = validator.validate(fornecedorDTO);
            if (!violacoes.isEmpty()) {
                System.out.println("⚠️ Violação de validação recebida do Kafka (Fornecedor):");
                violacoes.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));
                return;
            }

            System.out.println("✅ FornecedorDTO recebido e válido:");
            System.out.println(fornecedorDTO);

        } catch (Exception e) {
            System.err.println("❌ Erro ao desserializar ou validar FornecedorDTO:");
            e.printStackTrace();
        }
    }
}
