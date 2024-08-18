package com.hackaton.producs.generativeia.feign.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String role;
    private String content;
}