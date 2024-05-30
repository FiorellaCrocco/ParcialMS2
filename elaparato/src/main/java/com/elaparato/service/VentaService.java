package com.elaparato.service;

import com.elaparato.model.Producto;
import com.elaparato.model.Venta;
import com.elaparato.repository.IProductoRepository;
import com.elaparato.repository.IVentaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// Implementación concreta de la interfaz IVentaService, gestionando operaciones de
// negocio sobre productos utilizando un repositorio JPA.
@Service
public class VentaService implements IVentaService{

    // Inyección del repositorio que gestiona las operaciones CRUD para ventas.
    @Autowired
    private IVentaRepository ventaRepo;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private EntityManager em;

    // Retorna una lista de todas los ventas almacenados en la base de datos.
    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    // Guarda una venta en la base de datos.
    @Transactional
    public Venta saveVenta(Venta nuevaVenta) {
        // Asegúrate de que la lista de productos no está vacía
        if (!nuevaVenta.getListaProductos().isEmpty()) {
            // Extrae los IDs de los productos
            List<Integer> productoIds = nuevaVenta.getListaProductos().stream()
                    .map(Producto::getId)
                    .collect(Collectors.toList());

            // Recupera los productos existentes basados en los IDs
            List<Producto> productos = productoRepository.findAllById(productoIds);

            // Establece los productos recuperados a la nueva venta
            nuevaVenta.setListaProductos(productos);

            // Añade la nueva venta a cada producto para asegurar la bidireccionalidad
            productos.forEach(producto -> producto.getListaVentas().add(nuevaVenta));
        }

        // Guarda la venta con los productos asociados
        return ventaRepo.save(nuevaVenta);
    }


    // Elimina una venta en la base de datos.
    @Transactional
    @Override
    public void deleteVenta(int id) {
        Venta venta = ventaRepo.findById(id).orElse(null);
        if (venta != null) {
            // Elimina todas las referencias de esta venta en la lista de productos
            venta.getListaProductos().forEach(producto -> producto.getListaVentas().remove(venta));
            venta.getListaProductos().clear();

            // Guarda los cambios en los productos antes de eliminar la venta
            venta.getListaProductos().forEach(productoRepository::save);

            // Finalmente, elimina la venta
            ventaRepo.delete(venta);
        }
    }

    // Busca una venta por su Id en la base de datos.
    @Override
    public Venta findVenta(int id) {
       return ventaRepo.findById(id).orElse(null);
    }

    // Actualiza una venta en la base de datos.
    @Transactional
    public void editVenta(int id, Venta newData) {
        Venta venta = em.find(Venta.class, id);
        if (venta != null) {
            venta.setFecha(newData.getFecha());
            venta.setListaProductos(newData.getListaProductos());
            em.merge(venta); // Utiliza merge en lugar de persist para actualizar la entidad
        }
    }

}
