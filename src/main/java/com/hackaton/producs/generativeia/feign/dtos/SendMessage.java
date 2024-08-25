package com.hackaton.producs.generativeia.feign.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMessage {

    public static final String GPT_TURBO = "gpt-3.5-turbo";

    private String model;
    private List<Message> messages;
    private Double temperature;

    public static SendMessage builSendMessage(String messageString) {
        Message messageSystem = Message.builder().role("system").content("Tus respuestas van a ser en JSON solamente y debe ser un objeto json que contenga una lista de productos")
                .build();
        Message messageUser = Message.builder().role("user").content(messageString).build();
        return SendMessage.builder().messages(List.of(messageSystem, messageUser)).model(SendMessage.GPT_TURBO)
                .temperature(0.7).build();
    }
}