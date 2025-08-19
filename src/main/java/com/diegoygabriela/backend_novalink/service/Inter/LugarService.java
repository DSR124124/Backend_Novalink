package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Lugar;
import com.diegoygabriela.backend_novalink.entity.Lugar.CategoriaLugar;

import java.util.List;

public interface LugarService {
    void insert(Lugar lugar);
    List<Lugar> list();
    void delete(Long idLugar);
    Lugar listId(Long idLugar);
    
    // Métodos adicionales para la plataforma de parejas
    List<Lugar> findByNombreContainingIgnoreCase(String nombre);
    List<Lugar> findByCategoria(CategoriaLugar categoria);
    List<Lugar> findByEsFavorito(Boolean esFavorito);
    List<Lugar> findMasVisitados();
    List<Lugar> findMejorCalificados();
    
    // Métodos para gestionar favoritos
    void marcarComoFavorito(Long idLugar);
    void desmarcarComoFavorito(Long idLugar);
}

