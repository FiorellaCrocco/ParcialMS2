package com.elaparato.service;

import com.elaparato.model.Venta;

import java.util.List;

// Interfaz para definir los servicios que se pueden realizar sobre la entidad Venta. Proporciona abstracción
// para las operaciones CRUD y otras operaciones específicas sobre venta.
public interface IVentaService {

    // Método para obtener una lista de todas los ventas.
    public List<Venta> getVentas();

    // Método para guardar una nueva venta.
    public Venta saveVenta(Venta vent);

    // Método para eliminar una venta.
    public void deleteVenta(int id);

    // Método para buscar una venta por su id.
    public Venta findVenta(int id);

    // Método para editar una venta.
    public void editVenta(int id, Venta newData);

}
