package com.funcionario.cadastro.consumidorKafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funcionario.cadastro.dto.FuncDTO;
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
    public void consumirMensagem(ConsumerRecord<String, String> registro) {
        String mensagemJson = registro.value();

        try {
            FuncDTO funcDTO = objectMapper.readValue(mensagemJson, FuncDTO.class);

            Set<ConstraintViolation<FuncDTO>> violacoes = validator.validate(funcDTO);
            if (!violacoes.isEmpty()) {
                System.out.println("⚠️ Violação de validação recebida do Kafka:");
                violacoes.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));
                return;
            }

            System.out.println("✅ FuncDTO recebido e válido:");
            System.out.println(funcDTO);

        } catch (Exception e) {
            System.err.println("❌ Erro ao desserializar ou validar mensagem Kafka:");
            e.printStackTrace();
        }
    }
}
