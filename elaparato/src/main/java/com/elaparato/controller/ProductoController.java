package com.elaparato.controller;
import com.elaparato.model.Producto;
import com.elaparato.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST para manejar operaciones de negocio sobre producto.
@RestController
public class ProductoController {

    // Inyección de la interfaz del servicio de productos, que abstrae la lógica de negocio.
    @Autowired
    private IProductoService prodServ;

    // Crea un nuevo producto y la almacena en la base de datos;
    // retorna un mensaje indicando que la venta fue creada correctamente.
    @PostMapping("/productos/create")
    public String createProducto(@RequestBody Producto prod) {
        prodServ.saveProducto(prod);
        return "Producto creado correctamente";
    }

    // Obtiene y retorna todos los productos
    @GetMapping("/productos/getall")
    public List<Producto> getProductos () {
        return prodServ.getProductos();
    }

   // Modificar los datos de un producto existente y
   // retorna un mensaje indicando que el producto fue editado correctamente.
    @PutMapping("/productos/edit")
    public String editProducto(@RequestBody Producto prod) {
        prodServ.editProducto(prod);
        return "Producto editado correctamente";
    }
}
