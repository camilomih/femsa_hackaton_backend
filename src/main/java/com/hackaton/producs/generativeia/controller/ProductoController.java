package com.hackaton.producs.generativeia.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.producs.generativeia.dto.ProductsPlusSugets;
import com.hackaton.producs.generativeia.dto.PromptProducto;
import com.hackaton.producs.generativeia.dto.SaveProductos;
import com.hackaton.producs.generativeia.entities.Producto;
import com.hackaton.producs.generativeia.services.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private ProductoService productoService;

    ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.crearProducto(producto));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productoService.buscarProductoPorId(id));
    }

    @GetMapping("/listar/{pagina}")
    public ResponseEntity<ProductsPlusSugets> listarProductos(@PathVariable("pagina") Integer pagina, @RequestParam(required = false, name = "buscarProducto") String buscarProducto) {
        return ResponseEntity.ok(productoService.listarProductos(pagina, buscarProducto));
    }

    @PostMapping("/buscar-ia")
    public ResponseEntity<List<Producto>> buscarMedianteIA(@RequestBody PromptProducto producto) {
        return ResponseEntity.ok(productoService.buscarProductosByIA(producto.getProductoNombre()));
    }

    @PostMapping("/crear-varios")
    public ResponseEntity<List<Producto>> crearVariosProductos(@RequestBody SaveProductos productos) {
        return ResponseEntity.ok(productoService.crearVariosProductos(productos.getProductos()));
    }
}
