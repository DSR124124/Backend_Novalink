package com.diegoygabriela.backend_novalink.service.impl;
import com.diegoygabriela.backend_novalink.entity.Lugar;
import com.diegoygabriela.backend_novalink.entity.Lugar.CategoriaLugar;
import com.diegoygabriela.backend_novalink.repository.LugarRepository;
import com.diegoygabriela.backend_novalink.service.Inter.LugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarServiceImpl implements LugarService {

    @Autowired
    private LugarRepository lugarRepository;

    @Override
    public void insert(Lugar lugar) {
        lugarRepository.save(lugar);
    }

    @Override
    public List<Lugar> list() {
        return lugarRepository.findAll();
    }

    @Override
    public void delete(Long idLugar) {
        lugarRepository.deleteById(idLugar);
    }

    @Override
    public Lugar listId(Long idLugar) {
        return lugarRepository.findById(idLugar).orElse(new Lugar());
    }

    @Override
    public List<Lugar> findByNombreContainingIgnoreCase(String nombre) {
        return lugarRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Lugar> findByCategoria(CategoriaLugar categoria) {
        return lugarRepository.findByCategoria(categoria);
    }

    @Override
    public List<Lugar> findByEsFavorito(Boolean esFavorito) {
        return lugarRepository.findByEsFavorito(esFavorito);
    }

    @Override
    public List<Lugar> findMasVisitados() {
        return lugarRepository.findMasVisitados();
    }

    @Override
    public List<Lugar> findMejorCalificados() {
        return lugarRepository.findMejorCalificados();
    }
    
    @Override
    public void marcarComoFavorito(Long idLugar) {
        Lugar lugar = lugarRepository.findById(idLugar)
            .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));
        lugar.setEsFavorito(true);
        lugarRepository.save(lugar);
    }
    
    @Override
    public void desmarcarComoFavorito(Long idLugar) {
        Lugar lugar = lugarRepository.findById(idLugar)
            .orElseThrow(() -> new RuntimeException("Lugar no encontrado"));
        lugar.setEsFavorito(false);
        lugarRepository.save(lugar);
    }
}
