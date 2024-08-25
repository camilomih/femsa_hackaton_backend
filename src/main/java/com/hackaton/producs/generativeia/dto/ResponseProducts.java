package com.hackaton.producs.generativeia.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hackaton.producs.generativeia.entities.Producto;

import lombok.Data;

@Data
public class ResponseProducts {
    @JsonProperty("productos")
    List<Producto> productos;
}
