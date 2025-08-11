package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.DetalleRegalo;
import com.diegoygabriela.backend_novalink.repository.DetalleRegaloRepository;
import com.diegoygabriela.backend_novalink.service.Inter.DetalleRegaloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DetalleRegaloServiceImpl implements DetalleRegaloService {
    @Autowired
    private DetalleRegaloRepository detalleRegaloRepository;

    @Override
    public void insert(DetalleRegalo detalleRegalo) {
        detalleRegaloRepository.save(detalleRegalo);
    }

    @Override
    public List<DetalleRegalo> list() {
        return detalleRegaloRepository.findAll();
    }

    @Override
    public void delete(Long idDetalleRegalo) {
        detalleRegaloRepository.deleteById(idDetalleRegalo);
    }

    @Override
    public DetalleRegalo listId(Long idDetalleRegalo) {
        return detalleRegaloRepository.findById(idDetalleRegalo).orElse(new DetalleRegalo());
    }

    @Override
    public List<DetalleRegalo> findByParejaId(Long parejaId) {
        return detalleRegaloRepository.findByParejaIdOrderByFechaDesc(parejaId);
    }

    @Override
    public List<DetalleRegalo> findByRemitenteId(Integer remitenteId) {
        return detalleRegaloRepository.findByRemitenteIdUsuarioOrderByFechaDesc(remitenteId);
    }

    @Override
    public List<DetalleRegalo> findByReceptorId(Integer receptorId) {
        return detalleRegaloRepository.findByReceptorIdUsuarioOrderByFechaDesc(receptorId);
    }

    @Override
    public List<DetalleRegalo> findRegalosBetweenUsers(Integer usuario1Id, Integer usuario2Id) {
        return detalleRegaloRepository.findRegalosBetweenUsers(usuario1Id, usuario2Id);
    }

    @Override
    public List<DetalleRegalo> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return detalleRegaloRepository.findByFechaBetweenOrderByFechaDesc(fechaInicio, fechaFin);
    }

    @Override
    public List<DetalleRegalo> findByCitaId(Long citaId) {
        return detalleRegaloRepository.findByCitaIdOrderByFechaDesc(citaId);
    }

    @Override
    public List<DetalleRegalo> findByEventoId(Long eventoId) {
        return detalleRegaloRepository.findByEventoIdOrderByFechaDesc(eventoId);
    }

    @Override
    public Long countByParejaId(Long parejaId) {
        return detalleRegaloRepository.countByParejaId(parejaId);
    }

    @Override
    public List<DetalleRegalo> findRecentRegalos(int dias) {
        LocalDateTime fechaLimite = LocalDateTime.now().minusDays(dias);
        return detalleRegaloRepository.findRecentRegalos(fechaLimite);
    }
}