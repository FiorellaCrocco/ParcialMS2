package com.elaparato.controller;

import com.elaparato.model.Venta;
import com.elaparato.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Modificar los datos de una venta existente y
    // retorna un mensaje indicando que la venta fue editada correctamente.
    @PutMapping("/ventas/edit")
    public String editVenta(@RequestBody Venta vent) {
        ventServ.editVenta(vent);
        return "Venta editada correctamente";
    }

}
