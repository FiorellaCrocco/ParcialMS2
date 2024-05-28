package com.elaparato.controller;

import com.elaparato.model.User; // Importa la clase modelo User.
import com.elaparato.service.UserService; // Importa la clase UserService que contiene la lógica de negocio.
import org.springframework.http.ResponseEntity; // Importa ResponseEntity para personalizar las respuestas HTTP.
import org.springframework.web.bind.annotation.GetMapping; // Importa GetMapping para manejar peticiones GET.
import org.springframework.web.bind.annotation.PathVariable; // Importa PathVariable para vincular parámetros del método a variables de la plantilla de URI.
import org.springframework.web.bind.annotation.RequestMapping; // Importa RequestMapping para definir el mapeo de rutas generales.
import org.springframework.web.bind.annotation.RestController; // Importa RestController para definir esta clase como un controlador con @ResponseBody.


import java.util.List;
import java.util.Optional;

@RestController // Controlador REST para manejar operaciones de negocio sobre usuarios.
@RequestMapping("/users") // Mapea las peticiones HTTP a la ruta URL /users a todos los métodos de este controlador.

public class UserController {

    private UserService service;  // Declara una dependencia del UserService.

    // Inyección de la interfaz del servicio de usuarios, que abstrae la lógica de negocio.
    public UserController(UserService service) {
        this.service = service;
    }

    // Obtiene y retorna todos los usuarios
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // Obtiene por username y retorna todos los datos de ese usuario
    @GetMapping("/username/{userName}")
    public ResponseEntity<List<User>> findByUserName(@PathVariable String userName) {
        return ResponseEntity.ok(service.findByUserName(userName));
    }

    // Obtiene por userId y retorna todos los datos de ese usuario
    @GetMapping("/userId/{id}")
    public Optional<User> findById(@PathVariable String id) {
        return service.findById(id);
    }

}
