package com.diegoygabriela.backend_novalink.repository;

import com.diegoygabriela.backend_novalink.entity.Pareja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParejaRepository extends JpaRepository<Pareja, Long> {
    
    // Buscar pareja por usuario (puede ser usuario1 o usuario2)
    @Query("SELECT p FROM Pareja p WHERE p.usuario1.idUsuario = :usuarioId OR p.usuario2.idUsuario = :usuarioId")
    Optional<Pareja> findByUsuario1IdOrUsuario2Id(@Param("usuarioId") Long usuarioId);
}
