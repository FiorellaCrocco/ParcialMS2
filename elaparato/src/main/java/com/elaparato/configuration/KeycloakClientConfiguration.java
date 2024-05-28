package com.elaparato.configuration;

import org.keycloak.OAuth2Constants;  // Importa constantes usadas para configurar OAuth2 en Keycloak.
import org.keycloak.admin.client.Keycloak;  // Importa la clase Keycloak del cliente admin de Keycloak.
import org.keycloak.admin.client.KeycloakBuilder;  // Importa el constructor de la clase Keycloak para instanciar el cliente.
import org.springframework.beans.factory.annotation.Value;  // Importa la anotación Value para inyectar valores desde el archivo de propiedades.
import org.springframework.context.annotation.Bean;  // Importa la anotación Bean para definir beans en el contexto de Spring.
import org.springframework.context.annotation.Configuration;  // Importa la anotación Configuration que indica que la clase define beans de configuración.

@Configuration  // Marca la clase como fuente de definiciones de beans de Spring.
public class KeycloakClientConfiguration {

    @Value("${dh.keycloak.serverUrl}")  // Inyecta la URL del servidor de Keycloak desde el archivo de propiedades.
    private String serverUrl;

    @Value("${dh.keycloak.realm}")  // Inyecta el nombre del realm de Keycloak desde el archivo de propiedades.
    private String realm;

    @Value("${dh.keycloak.clientId}")  // Inyecta el ID del cliente desde el archivo de propiedades.
    private String clientId;

    @Value("${dh.keycloak.clientSecret}")  // Inyecta el secreto del cliente desde el archivo de propiedades.
    private String clientSecret;

    @Bean  // Define un método bean en Spring que devuelve una nueva instancia del cliente Keycloak.
    public Keycloak getInstance() {
        return KeycloakBuilder.builder()  // Crea un nuevo constructor de Keycloak.
                .serverUrl(serverUrl)  // Configura la URL del servidor de Keycloak.
                .realm(realm)  // Configura el realm al que el cliente se conectará.
                .clientId(clientId)  // Configura el ID del cliente.
                .clientSecret(clientSecret)  // Configura el secreto del cliente.
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)  // Establece el tipo de concesión para el cliente.
                .build();  // Construye y retorna la instancia del cliente Keycloak configurado.
    }

}
