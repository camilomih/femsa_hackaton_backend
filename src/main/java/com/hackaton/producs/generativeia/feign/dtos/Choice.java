package com.hackaton.producs.generativeia.feign.dtos;

import lombok.Data;

@Data
public class Choice {
    private Integer index;
    private Message message;
    private String finish_reason;
}
