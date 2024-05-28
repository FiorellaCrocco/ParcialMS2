package com.apigatewayelaparato.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration // Anotación para definir que esta clase es de configuración de Spring
public class SecurityConfig {

    @Bean  // Anotación que define un bean en el contexto de Spring, aquí configura la cadena de filtros de seguridad
    public SecurityWebFilterChain springSecurityFilterChain (ServerHttpSecurity http) {
        http
                .authorizeExchange()  // Inicia la configuración de autorización para las solicitudes HTTP
                .anyExchange()  // Aplica la configuración a cualquier solicitud
                .authenticated()  // Requiere que todas las solicitudes sean autenticadas
                .and()  // Método para encadenar configuraciones HTTP
                .oauth2Login();  // Habilita el inicio de sesión OAuth2, redirigiendo a la página de login de OAuth2 si no está autenticado

        return http.build();  // Construye y devuelve el objeto SecurityWebFilterChain configurado
    }
}


