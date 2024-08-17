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
}