package com.hackaton.producs.generativeia.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Producto> listarProductos(int initPage) {
        Pageable page = PageRequest.of(initPage, 10); 
        
        return this.productoRepository.findAll(page);
    }

    public Producto buscarProductoPorId(Integer id) {
        return this.productoRepository.findById(id).orElseThrow();
    }
}
