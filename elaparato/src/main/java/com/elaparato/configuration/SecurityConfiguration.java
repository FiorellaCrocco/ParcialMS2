package com.elaparato.configuration;

import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor // Lombok: Anotación que genera un constructor con argumentos requeridos para las finalidades
@Configuration // Anotación que indica que esta clase es usada por Spring IoC como configuración
@EnableWebSecurity // Activa la configuración de seguridad web de Spring Security
public class SecurityConfiguration {

    // Inyección de una dependencia para convertir JWT a autenticación
    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    // Constantes para roles de usuarios
    public static final String ADMINISTRADOR = "rol_administrador";

    public static final String REPOSITOR = "rol_repositor";

    public static final String VENDEDOR = "rol_vendedor";

    @Bean // Anotación que indica que el método produce un bean manejado por Spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests() // Autoriza peticiones basadas en HttpServletRequest

                // Especifica las rutas y los roles necesarios para operaciones en productos
                .requestMatchers(HttpMethod.POST, "/productos/create", "/productos/**").hasAnyRole(REPOSITOR, ADMINISTRADOR)
                .requestMatchers(HttpMethod.GET, "/productos/getall", "/productos/**").hasAnyRole(REPOSITOR, ADMINISTRADOR)
                .requestMatchers(HttpMethod.GET, "/productos/**", "/productos/**").hasAnyRole(REPOSITOR, ADMINISTRADOR)
                .requestMatchers(HttpMethod.PUT, "/productos/edit/**", "/productos/**").hasAnyRole(REPOSITOR, ADMINISTRADOR)
                .requestMatchers(HttpMethod.DELETE, "/productos/**").hasAnyRole(REPOSITOR, ADMINISTRADOR)

                // Especifica las rutas y los roles necesarios para operaciones en ventas
                .requestMatchers(HttpMethod.POST, "/ventas/create", "/ventas/**").hasAnyRole(VENDEDOR, ADMINISTRADOR)
                .requestMatchers(HttpMethod.GET, "/ventas/getall", "/ventas/**").hasAnyRole(VENDEDOR, ADMINISTRADOR)
                .requestMatchers(HttpMethod.GET, "/ventas/**", "/ventas/**").hasAnyRole(VENDEDOR, ADMINISTRADOR)
                .requestMatchers(HttpMethod.PUT, "/ventas/edit/**", "/ventas/**").hasAnyRole(VENDEDOR, ADMINISTRADOR)
                .requestMatchers(HttpMethod.DELETE, "/ventas/**").hasAnyRole(VENDEDOR, ADMINISTRADOR)

                // Especifica las rutas y los roles necesarios para operaciones en usuarios
                .requestMatchers(HttpMethod.GET, "/users/all").hasAnyRole(ADMINISTRADOR)
                .requestMatchers(HttpMethod.GET, "/users/username/**").hasAnyRole(ADMINISTRADOR)
                .requestMatchers(HttpMethod.GET, "/users/userId/**").hasAnyRole(ADMINISTRADOR)

                .anyRequest().authenticated();  // Todas las otras peticiones requieren autenticación

        http.oauth2ResourceServer()  // Habilita soporte para servidor de recursos OAuth2
                .jwt() // Uso de JWT como formato de token
                .jwtAuthenticationConverter(jwtAuthenticationConverter); // Convertidor para JWT a autenticación
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // No se crean sesiones para autenticación
        return http.build();  // Construye la cadena de filtros de seguridad
    }

}
