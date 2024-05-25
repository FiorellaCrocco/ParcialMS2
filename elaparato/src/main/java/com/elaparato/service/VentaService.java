package com.elaparato.service;

import com.elaparato.model.Venta;
import com.elaparato.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementación concreta de la interfaz IVentaService, gestionando operaciones de
// negocio sobre productos utilizando un repositorio JPA.
@Service
public class VentaService implements IVentaService{

    // Inyección del repositorio que gestiona las operaciones CRUD para ventas.
    @Autowired
    private IVentaRepository ventaRepo;

    // Retorna una lista de todas los ventas almacenados en la base de datos.
    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    // Guarda una venta en la base de datos.
    @Override
    public void saveVenta(Venta vent) {
        ventaRepo.save(vent);
    }

    // Elimina una venta en la base de datos.
    @Override
    public void deleteVenta(int id) {
        ventaRepo.deleteById(id);
    }

    // Busca una venta por su Id en la base de datos.
    @Override
    public Venta findVenta(int id) {
       return ventaRepo.findById(id).orElse(null);
    }

    // Actualiza una venta en la base de datos.
    @Override
    public void editVenta(Venta vent) {
        this.saveVenta(vent);
    }

    }
