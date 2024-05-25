package com.elaparato.repository;

import com.elaparato.model.Producto; // Importación necesaria para trabajar con la entidad Producto
import org.springframework.data.jpa.repository.JpaRepository; // Importación de JpaRepository, que proporciona funcionalidades CRUD para entidades JPA
import org.springframework.stereotype.Repository; // Importación de la anotación Repository, que indica que esta interfaz es un componente de Spring gestionando operaciones de base de datos

@Repository // Indica que este es un bean y que debe ser gestionado por el contexto de Spring.
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
}
