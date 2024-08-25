package com.hackaton.producs.generativeia.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hackaton.producs.generativeia.entities.Producto;

import lombok.Data;

@Data
public class ProductsPlusSugets {
    private Page<Producto> productos;
    @JsonInclude(Include.NON_NULL)
    private List<Producto> sugerenciasIA;
}
