package com.hackaton.producs.generativeia.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackaton.producs.generativeia.dto.ProductsPlusSugets;
import com.hackaton.producs.generativeia.dto.ResponseProducts;
import com.hackaton.producs.generativeia.entities.Producto;
import com.hackaton.producs.generativeia.feign.client.OpenIAFeignClient;
import com.hackaton.producs.generativeia.feign.dtos.ResponseOpen;
import com.hackaton.producs.generativeia.feign.dtos.SendMessage;
import com.hackaton.producs.generativeia.repositories.ProductoRepository;
import com.hackaton.producs.generativeia.specifications.ProductSpecification;

@Service
public class ProductoService {

    private final static String PROMP_SEARCH_PRODUCTS = "Busca similitudes en mexico y retorna los datos, nombre,descripcion,precio en double,marca,codigoSAT,unidadSAT lista minimo 5 en México del producto: ";

    private ProductoRepository productoRepository;
    private OpenIAFeignClient openIAFeignClient;

    ProductoService(ProductoRepository productoRepository, OpenIAFeignClient openIAFeignClient) {
        this.productoRepository = productoRepository;
        this.openIAFeignClient = openIAFeignClient;
    }

    public Producto crearProducto(Producto producto) {
        producto.setGeneratedByIa(false);
        return this.productoRepository.save(producto);
    }

    public ProductsPlusSugets listarProductos(int initPage, String nombre) {
        ProductsPlusSugets response = new ProductsPlusSugets();
        Pageable page = PageRequest.of(initPage, 10);
        if (nombre == null) {
            response.setProductos(productoRepository.findAll(page));
            return response;
        }
        response.setProductos(productoRepository.findAll(ProductSpecification.nameLike(nombre), page));
        response.setSugerenciasIA(buscarProductosByIA(nombre));
        return response;
    }

    public Producto buscarProductoPorId(Integer id) {
        return this.productoRepository.findById(id).orElseThrow();
    }

    public List<Producto> buscarProductosByIA(String producto) {
        ResponseOpen responseOpen = this.openIAFeignClient
                .askChatGPT(SendMessage.builSendMessage(PROMP_SEARCH_PRODUCTS.concat(" " + producto)));

        String productosJson = responseOpen.getChoices().get(0).getMessage().getContent();

        return convertTResponseProducts(productosJson);
    }

    public List<Producto> convertTResponseProducts(String json) {
        try {

            System.out.println(json);
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseProducts responseProducts = objectMapper.readValue(json, ResponseProducts.class);

            List<Producto> productos = responseProducts.getProductos();

            productos = productos.stream().map(producto -> {
                producto.setGeneratedByIa(true);
                return producto;
            }).toList();

            return productos;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Producto> crearVariosProductos(List<Producto> productos) {
        List<Producto> productosGuardados = productoRepository.saveAll(productos);
        return productosGuardados;
    }
}
