package com.hackaton.producs.generativeia.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseProducts {
    @JsonProperty("productos")
    List<ProductosIA> productos;
}
