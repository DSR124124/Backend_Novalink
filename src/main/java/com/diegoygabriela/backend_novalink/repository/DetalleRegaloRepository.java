package com.diegoygabriela.backend_novalink.repository;

import com.diegoygabriela.backend_novalink.entity.DetalleRegalo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRegaloRepository extends JpaRepository<DetalleRegalo, Long> {
    // Aquí se pueden agregar métodos personalizados de consulta si son necesarios
}