package com.elaparato.repository;

import com.elaparato.model.User; // Importa la clase User del modelo.
import org.keycloak.admin.client.Keycloak; // Importa la clase Keycloak para interactuar con el servidor Keycloak.
import org.keycloak.representations.idm.UserRepresentation; // Importa la representación de un usuario de Keycloak.
import org.springframework.beans.factory.annotation.Value; // Importa la anotación para inyectar valores desde el archivo de propiedades.
import org.springframework.stereotype.Repository; // Indica que la clase es un componente de Spring que se utiliza para la persistencia de datos.

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; // Importa herramientas para trabajar con streams y realizar operaciones de colección.

@Repository // Anotación que designa la clase como un Repositorio, un tipo especializado de @Component.
public class UserRepository implements IUserRepository{ // Implementa la interfaz IUserRepository que define métodos CRUD para usuarios.

    private final Keycloak keycloakClient; // Cliente de Keycloak para realizar operaciones API.

    @Value("${dh.keycloak.realm}") // Inyecta el valor de 'realm' del archivo de propiedades.
    private String realm;

    public UserRepository(Keycloak keycloakClient) { // Constructor que inyecta la dependencia de Keycloak.
        this.keycloakClient = keycloakClient;
    }

    @Override
    public List<User> findAll() { // Método para obtener todos los usuarios desde Keycloak.
        return keycloakClient.realm(realm).users().list().stream() // Usa Keycloak API para listar usuarios y los convierte a la entidad User.
                .map(this::toUser).collect(Collectors.toList());
    }

    private User toUser(UserRepresentation userRepresentation) { // Método helper para convertir UserRepresentation a User.
        return User.builder()
                .id(userRepresentation.getId())
                .userName(userRepresentation.getUsername())
                .email(userRepresentation.getEmail())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .build();
    }

    @Override
    public List<User> findByUserName(String userName) { // Método para buscar usuarios por nombre de usuario.
        List<UserRepresentation> userRepresentation = keycloakClient
                .realm(realm)
                .users()
                .search(userName);
        return userRepresentation.stream().map(this::toUser) // Convierte los resultados a la entidad User.
                .collect(Collectors.toList());
    }

    private User fromUserRepresentation(UserRepresentation userRepresentation) { // Método adicional para convertir UserRepresentation a User.
        return new User(userRepresentation.getId(), userRepresentation.getUsername()
                , userRepresentation.getEmail(), userRepresentation.getFirstName(), userRepresentation.getLastName());
    }

    @Override
    public Optional<User> findById(String id) { // Método para buscar un usuario por ID.
        UserRepresentation userRepresentation = null;
        try {
            userRepresentation = keycloakClient // Intenta obtener el usuario desde Keycloak.
                    .realm(realm)
                    .users()
                    .get(id)
                    .toRepresentation();
        } catch (javax.ws.rs.NotFoundException e) { // Maneja el caso donde el usuario no se encuentra.
            return Optional.empty();
        }

        if (userRepresentation != null) { // Si se encuentra el usuario, lo convierte y lo devuelve encapsulado en un Optional.
            return Optional.of(toUser(userRepresentation));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public User deleteUserById(String id) { // Método para eliminar un usuario por ID (aún no implementado).
        return null;
    }
}
