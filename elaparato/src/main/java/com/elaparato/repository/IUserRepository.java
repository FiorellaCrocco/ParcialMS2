package com.elaparato.repository;

import com.elaparato.model.User;  // Importación necesaria para trabajar con la entidad Usuario
import org.springframework.stereotype.Repository; // Importación de la anotación Repository, que indica que esta interfaz es un componente de Spring gestionando operaciones de base de datos

import java.util.List;
import java.util.Optional;

@Repository // Indica que este es un bean y que debe ser gestionado por el contexto de Spring.
public interface IUserRepository {

    List<User> findAll();  // Método para obtener todos los usuarios.

    List<User> findByUserName(String userName);  // Método para buscar usuarios por su nombre de usuario.

    Optional<User> findById(String id);  // Método para buscar un usuario por su ID, el resultado es un Optional que puede o no contener un usuario.

    User deleteUserById(String id);  // Método para eliminar un usuario por su ID y devolver el usuario eliminado.

}
