package com.diegoygabriela.backend_novalink.repository;

import com.diegoygabriela.backend_novalink.entity.Lugar;
import com.diegoygabriela.backend_novalink.entity.Lugar.CategoriaLugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Long> {
    
    // Buscar lugares por categoría
    List<Lugar> findByCategoria(CategoriaLugar categoria);
    
    // Buscar lugares favoritos
    List<Lugar> findByEsFavorito(Boolean esFavorito);
    
    // Buscar lugares por nombre (búsqueda parcial)
    List<Lugar> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar lugares más visitados
    @Query("SELECT l FROM Lugar l WHERE l.vecesVisitado > 0 ORDER BY l.vecesVisitado DESC")
    List<Lugar> findMasVisitados();
    
    // Buscar lugares mejor calificados
    @Query("SELECT l FROM Lugar l WHERE l.ratingPromedio > 0 ORDER BY l.ratingPromedio DESC")
    List<Lugar> findMejorCalificados();
    
    // Buscar lugares por rango de coordenadas (para mapa)
    @Query("SELECT l FROM Lugar l WHERE l.latitud BETWEEN :latMin AND :latMax " +
           "AND l.longitud BETWEEN :lonMin AND :lonMax")
    List<Lugar> findByCoordenadasRange(@Param("latMin") Double latMin, @Param("latMax") Double latMax,
                                      @Param("lonMin") Double lonMin, @Param("lonMax") Double lonMax);
    
    // Contar lugares por categoría
    Long countByCategoria(CategoriaLugar categoria);
    
    // Buscar lugares favoritos más visitados
    @Query("SELECT l FROM Lugar l WHERE l.esFavorito = true ORDER BY l.vecesVisitado DESC")
    List<Lugar> findFavoritosMasVisitados();
}

