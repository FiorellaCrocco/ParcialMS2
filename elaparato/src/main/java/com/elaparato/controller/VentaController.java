package com.elaparato.controller;

import com.elaparato.model.Venta;
import com.elaparato.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST para manejar operaciones de negocio sobre ventas.
@RestController
public class VentaController {

    // Inyección de la interfaz del servicio de ventas, que abstrae la lógica de negocio.
    @Autowired
    private IVentaService ventServ;

    // Crea una nueva venta y la almacena en la base de datos;
    // retorna un mensaje indicando que la venta fue creada correctamente.
    @PostMapping("/ventas/create")
    public String createVentao(@RequestBody Venta vent) {
        ventServ.saveVenta(vent);
        return "Venta creada correctamente";
    }

    // Obtiene y retorna todas las ventas almacenadas en la base de datos
    @GetMapping("/ventas/getall")
    public List<Venta> getVentas () {
        return ventServ.getVentas();
    }

    @GetMapping("/ventas/{id}")
    public Venta getVentaById(@PathVariable Integer id){
        Venta venta = ventServ.findVenta(id);
        return venta;
    }

    // Modificar los datos de una venta existente y
    // retorna un mensaje indicando que la venta fue editada correctamente.
    @PutMapping("/ventas/edit/{id}")
    public String editVenta(@PathVariable Integer id, @RequestBody Venta vent) {
        ventServ.editVenta(vent);
        return "Venta editada correctamente";
    }

    // Elimina un venta existente
    @DeleteMapping("/ventas/{id}")
    public String deleteProducto(@PathVariable Integer id){
        ventServ.deleteVenta(id);
        return "Venta eliminado correctamente";
    }
}
