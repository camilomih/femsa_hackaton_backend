package com.hackaton.producs.generativeia.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tbl_prodcto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String marca;
    private String codigoSAT;
    private String unidadSAT;

}
