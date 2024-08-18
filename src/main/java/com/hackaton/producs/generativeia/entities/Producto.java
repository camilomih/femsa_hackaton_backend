package com.hackaton.producs.generativeia.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity(name = "tbl_prodcto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonInclude(Include.NON_NULL)
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String marca;
    private String codigoSAT;
    private String unidadSAT;

    @Transient
    private Boolean generatedByIa = false;

}
