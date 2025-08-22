package com.diegoygabriela.backend_novalink.repository;

import com.diegoygabriela.backend_novalink.entity.CategoriaCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaCitaRepository extends JpaRepository<CategoriaCita, Long> {
    
    // Consulta para contar citas por categoría
    @Query("SELECT COUNT(c) FROM Cita c WHERE c.categoriaCita.id = :idCategoria")
    long countCitasByCategoria(@Param("idCategoria") Long idCategoria);
}
