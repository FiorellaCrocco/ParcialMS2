package com.elaparato.service;

import com.elaparato.model.Producto;

import java.util.List;

// Interfaz para definir los servicios que se pueden realizar sobre la entidad Producto. Proporciona abstracción
// para las operaciones CRUD y otras operaciones específicas sobre productos.
public interface IProductoService {

    // Método para obtener una lista de todos los productos.
    public List<Producto> getProductos();

    // Método para guardar un nuevo productos.
    public void saveProducto(Producto prod);

    // Método para eliminar un productos.
    public void deleteProducto(int id);

    // Método para buscar un productos por su id.
    public Producto findProducto(int id);

    // Método para editar un productos.
    public void editProducto(Producto prod);

}
