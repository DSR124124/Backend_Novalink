package com.diegoygabriela.backend_novalink.repository;

import com.diegoygabriela.backend_novalink.entity.Pareja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParejaRepository extends JpaRepository<Pareja, Long> {

    // Buscar pareja por usuario (usuario1 o usuario2)
    @Query("SELECT p FROM Pareja p WHERE p.usuario1.idUsuario = :usuarioId OR p.usuario2.idUsuario = :usuarioId")
    Optional<Pareja> findByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    // Verificar si un usuario ya tiene pareja
    @Query("SELECT COUNT(p) > 0 FROM Pareja p WHERE p.usuario1.idUsuario = :usuarioId OR p.usuario2.idUsuario = :usuarioId")
    boolean existsByUsuarioId(@Param("usuarioId") Long usuarioId);
}
