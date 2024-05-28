package com.elaparato.model;

import lombok.*;  // Importa todas las anotaciones comunes de Lombok.

@Getter @Setter  // Genera automáticamente métodos getter y setter para cada campo de la clase.
@Builder  // Proporciona un patrón de construcción para instancias de esta clase.
@AllArgsConstructor  // Genera un constructor con todos los atributos como parámetros.
@NoArgsConstructor  // Genera un constructor sin parámetros.
public class User {

    private String id;  // Campo que guarda el identificador único del usuario.

    private String userName;  // Campo que guarda el nombre de usuario.

    private String email;  // Campo que guarda el correo electrónico del usuario.

    private String firstName;  // Campo que guarda el primer nombre del usuario.

    private String lastName;  // Campo que guarda el apellido del usuario.

}
