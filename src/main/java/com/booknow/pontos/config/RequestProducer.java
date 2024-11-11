package com.booknow.pontos.config;

import BookNow_app.BookNow.Usuario.Entity.User;
import com.booknow.pontos.domain.model.TransacaoPontos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RequestProducer {

    @Value("puc-2024")
    private String topicoRequest;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public String sendMessage(TransacaoPontos pontos) throws JsonProcessingException {
        String orderAsMessage = objectMapper.writeValueAsString(pontos);
        kafkaTemplate.send(topicoRequest, orderAsMessage);
        return "Pagamento realizado";
    }

    public Integer sendMessage(Integer pontosAtualizado) {
        return pontosAtualizado;
    }
}
