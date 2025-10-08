package com.funcionario.cadastro.produtorKafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProdutorKafka {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProdutorKafka(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void enviarMensagemFunci(String mensagemFuncionario){
        kafkaTemplate.send("funcionarios.criados", mensagemFuncionario);
    }
    public void enviarMensagemFornecedor(String mensagemFornecedor){
        kafkaTemplate.send("fornecedores.criados", mensagemFornecedor);
    }
    public void enviarMensagemProduto(String mensagemProduto){
        kafkaTemplate.send("produtos.criados", mensagemProduto);
    }
}
