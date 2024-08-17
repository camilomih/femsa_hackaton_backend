package com.hackaton.producs.generativeia.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hackaton.producs.generativeia.entities.Producto;
import com.hackaton.producs.generativeia.repositories.ProductoRepository;

@Service
public class ProductoService {

    private ProductoRepository productoRepository;

    ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crearProducto(Producto producto) {
        return this.productoRepository.save(producto);
    }

    public List<Producto> listarProductos() {
        return this.productoRepository.findAll();
    }

    public Producto buscarProductoPorId(Integer id) {
        return this.productoRepository.findById(id).orElseThrow();
    }
}
