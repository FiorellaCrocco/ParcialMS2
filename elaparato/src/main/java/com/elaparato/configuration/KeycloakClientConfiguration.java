package com.elaparato.configuration;

// Importaciones necesarias para trabajar con Keycloak y configuración de Spring
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Clase de configuración que define la configuración del cliente de Keycloak.
// Esta configuración permite la integración con el servidor de Keycloak para operaciones de administración.
@Configuration
public class KeycloakClientConfiguration {

    // Inyección de valores desde el archivo de propiedades de la aplicación

    @Value("${dh.keycloak.serverUrl}")
    private String serverUrl; // URL del servidor de Keycloak

    @Value("${dh.keycloak.realm}")
    private String realm; // Realm en Keycloak para autenticación y autorización

    @Value("${dh.keycloak.clientId}")
    private String clientId;  // Id del cliente registrado en Keycloak

    @Value("${dh.keycloak.clientSecret}")
    private String clientSecret; // Secreto del cliente para autenticación segura

    // Define un bean de Spring para el cliente de Keycloak. Este puede ser autoinyectado en cualquier componente de Spring para interactuar con Keycloak.
    // Retorna una instancia de Keycloak configurada para comunicarse con el servidor.
    @Bean
    public Keycloak getInstance() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)  // Configura la URL del servidor de Keycloak
                .realm(realm)  // Configura el realm de Keycloak
                .clientId(clientId)  // Configura el ID del cliente
                .clientSecret(clientSecret)  // Configura el secreto del cliente
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)  // Utiliza el tipo de concesión de credenciales del cliente para la autenticación
                .build();  // Construye y retorna la instancia del cliente de Keycloak
    }
}
