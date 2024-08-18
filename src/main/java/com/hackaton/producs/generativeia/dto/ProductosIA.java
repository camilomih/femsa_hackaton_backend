package com.hackaton.producs.generativeia.dto;

import com.hackaton.producs.generativeia.entities.Producto;

import lombok.Data;

@Data
public class ProductosIA {
    private String nombre;
    private String descripcion;
    private String precio;
    private String marca;
    private String codigoSAT;
    private String unidadSAT;

    public Producto toEntity() {
        Producto producto = new Producto();
        producto.setNombre(this.nombre);
        producto.setDescripcion(this.descripcion);
        producto.setPrecio(Double.parseDouble(precio));
        producto.setMarca(marca);
        producto.setCodigoSAT(codigoSAT);
        producto.setUnidadSAT(unidadSAT);

        return producto;
    }
}
