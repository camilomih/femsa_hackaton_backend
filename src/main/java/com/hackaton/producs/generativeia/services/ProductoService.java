package com.hackaton.producs.generativeia.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackaton.producs.generativeia.dto.ProductosIA;
import com.hackaton.producs.generativeia.dto.ResponseProducts;
import com.hackaton.producs.generativeia.entities.Producto;
import com.hackaton.producs.generativeia.feign.client.OpenIAFeignClient;
import com.hackaton.producs.generativeia.feign.dtos.ResponseOpen;
import com.hackaton.producs.generativeia.feign.dtos.SendMessage;
import com.hackaton.producs.generativeia.repositories.ProductoRepository;

@Service
public class ProductoService {

    private final static String PROMP_SEARCH_PRODUCTS = "Busca similitudes en mexico y retorna los datos, nombre,descripcion,precio,marca,codigoSAT,unidadSAT en México del producto: ";

    private ProductoRepository productoRepository;
    private OpenIAFeignClient openIAFeignClient;

    ProductoService(ProductoRepository productoRepository, OpenIAFeignClient openIAFeignClient) {
        this.productoRepository = productoRepository;
        this.openIAFeignClient = openIAFeignClient;
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

    public List<ProductosIA> buscarProductosByIA(String producto) {
        ResponseOpen responseOpen = this.openIAFeignClient
                .askChatGPT(SendMessage.builSendMessage(PROMP_SEARCH_PRODUCTS.concat(" " + producto)));

        
        String productosJson = responseOpen.getChoices().get(0).getMessage().getContent();

        return convertTResponseProducts(productosJson);
    }

    public List<ProductosIA> convertTResponseProducts(String json){
        try {

            System.out.println(json);
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseProducts responseProducts = objectMapper.readValue(json, ResponseProducts.class);

            return responseProducts.getProductos();
        } catch (Exception e) {
            return null;
        }
    }
}
