package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.DetalleRegalo;
import com.diegoygabriela.backend_novalink.repository.DetalleRegaloRepository;
import com.diegoygabriela.backend_novalink.service.Inter.DetalleRegaloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void delete(int idDetalleRegalo) {
        detalleRegaloRepository.deleteById((long) idDetalleRegalo);
    }

    @Override
    public DetalleRegalo listId(int idDetalleRegalo) {
        return detalleRegaloRepository.findById((long) idDetalleRegalo).orElse(new DetalleRegalo());
    }
}