package com.hackaton.producs.generativeia.feign.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ResponseOpen {
    private String id;
    private String object;
    private Date created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
}