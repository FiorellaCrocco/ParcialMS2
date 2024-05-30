package com.elaparato.model;

// Importaciones necesarias para las anotaciones JPA y otras funcionalidades de Java
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter // Anotaciones de Lombok para generar automáticamente los métodos getters y setters
@Entity // Indica que esta clase es una entidad JPA que será mapeada a una tabla en la base de datos
public class Venta {

    @Id // Anotación para indicar que este campo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotación para especificar que el valor de la clave primaria es generado automáticamente mediante una secuencia, lo cual es común en bases de datos que soportan secuencias
    private int id_venta; // Campo que almacena el identificador único de cada venta

    private Date fecha;  // Campo que almacena la fecha en que se realiza la venta

    // Relación muchos a muchos entre 'Venta' y 'Producto'. El 'mappedBy' indica que la entidad propietaria
    // de la relación es 'Producto' y 'listaVentas' es el nombre del campo en la clase 'Producto' que mapea la relación
    @ManyToMany (mappedBy = "listaVentas")
    private List<Producto> listaProductos;


}
