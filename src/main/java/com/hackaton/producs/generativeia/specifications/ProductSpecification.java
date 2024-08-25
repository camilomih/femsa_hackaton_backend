package com.hackaton.producs.generativeia.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.hackaton.producs.generativeia.entities.Producto;

public class ProductSpecification {
    public static Specification<Producto> nameLike(String nameLike){
        return (root, query, builder) -> builder.like(builder.lower(root.get("nombre")), "%" + nameLike.toLowerCase() + "%");
    }
}
