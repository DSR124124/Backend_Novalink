package com.diegoygabriela.backend_novalink.repository;


import com.diegoygabriela.backend_novalink.entity.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Long> {
    // Puedes agregar métodos personalizados si los necesitas, por ejemplo:
    // Optional<Lugar> findByNombre(String nombre);
}

