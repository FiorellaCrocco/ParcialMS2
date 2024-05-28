package com.elaparato.configuration;

import lombok.Data; // Lombok annotation to provide getters, setters, equals, hashCode, and toString automatically
import org.springframework.boot.context.properties.ConfigurationProperties; // Anotación para vincular propiedades del archivo de configuración a esta clase
import org.springframework.context.annotation.Configuration; // Anotación que indica que esta clase es una configuración de Spring
import org.springframework.validation.annotation.Validated; // Anotación que habilita la validación de las propiedades configuradas

@Data // Utiliza Lombok para simplificar la creación de métodos getters y setters
@Validated // Habilita la validación para esta clase de configuración
@Configuration // Declara esta clase como un bean de configuración de Spring
@ConfigurationProperties(prefix = "jwt.auth.converter") // Especifica el prefijo de las propiedades de configuración que esta clase vinculará
public class JwtAuthConverterProperties {

    private String resourceId; // Define una propiedad que almacenará el identificador del recurso utilizado en JWT

    private String principalAttribute; // Define una propiedad que almacenará el atributo del JWT que representa al usuario principal

}
