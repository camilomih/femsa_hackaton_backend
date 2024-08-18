package com.hackaton.producs.generativeia.dto;

import java.util.List;

import com.hackaton.producs.generativeia.entities.Producto;

import lombok.Data;

@Data
public class SaveProductos {
    private List<Producto> productos;
}
