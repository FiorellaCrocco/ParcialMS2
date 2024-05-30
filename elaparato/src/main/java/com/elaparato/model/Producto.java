package com.elaparato.model;

// Importaciones necesarias para las anotaciones JPA y otras funcionalidades de Java
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter // Anotaciones de Lombok para generar automáticamente los métodos getters y setters
@Entity // Indica que esta clase es una entidad JPA que será mapeada a una tabla en la base de datos
public class Producto {

    @Id // Anotación para indicar que este campo es la clave primaria de la entidad
    @GeneratedValue(strategy= GenerationType.IDENTITY) // Define que el ID es generado automáticamente autoincremental
    private int id;  // Identificador único del producto

    private String nombre; // Nombre del producto
    private String descripcion; // Descripción del producto
    private Double precio; // Precio del producto
    private int cantidad; // Cantidad en inventario del producto

    @ManyToMany(cascade = CascadeType.ALL) // Define una relación de muchos a muchos con la entidad Venta
    @JsonIgnore  // Evita problemas de recursión infinita al serializar la entidad a JSON
    @JoinTable(
            name = "producto_lista_ventas",
            joinColumns = @JoinColumn(name = "lista_productos_id"),
            inverseJoinColumns = @JoinColumn(name = "lista_ventas_id")
    )
    private List<Venta> listaVentas; // Lista de ventas en las que el producto ha sido incluido

    // Constructor sin parámetros necesario para JPA
    public Producto() {
    }

    // Constructor con parámetros para inicializar el objeto Producto
    public Producto(int id, String nombre, String descripcion, Double precio, int cantidad, List<Venta> listaVentas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.listaVentas = listaVentas;
    }

}
