package com.elaparato.service;


import com.elaparato.model.User;
import com.elaparato.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Implementación concreta de la interfaz IVentaService, gestionando operaciones de
// negocio sobre productos utilizando un repositorio JPA.
@Service
public class UserService {

    // Inyección del repositorio que gestiona las operaciones CRUD para usuarios.
    private IUserRepository repository;

    // Declaración de constructor para el repository.
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    // Retorna una lista de todas los usuarios.
    public List<User> findAll() {
        return repository.findAll();
    }

    // Retorna una lista de todas los usuarios con cierto username.
    public List<User> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    // Retorna el usuario segun su Id.
    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

}
