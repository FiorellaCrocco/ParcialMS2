package com.elaparato.service;
import com.elaparato.model.Producto;
import com.elaparato.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementación concreta de la interfaz IProductoService, gestionando operaciones de
// negocio sobre productos utilizando un repositorio JPA.
@Service
public class ProductoService implements IProductoService{

    // Inyección del repositorio que gestiona las operaciones CRUD para productos.
    @Autowired
    private IProductoRepository prodRepo;

    // Retorna una lista de todos los productos almacenados en la base de datos.
    @Override
    public List<Producto> getProductos() {
        return prodRepo.findAll();
    }

    // Guarda un producto en la base de datos.
    @Override
    public void saveProducto(Producto prod) {
        prodRepo.save(prod);
    }

    // Elimina un producto en la base de datos.
    @Override
    public void deleteProducto(int id) {
        prodRepo.deleteById(id);
    }

    // Busca un producto por su Id en la base de datos.
    @Override
    public Producto findProducto(int id) {
        return prodRepo.findById(id).orElse(null);
    }

    // Actualiza un producto en la base de datos.
    @Override
    public void editProducto(Producto prod) {
        this.saveProducto(prod);
    }

}
