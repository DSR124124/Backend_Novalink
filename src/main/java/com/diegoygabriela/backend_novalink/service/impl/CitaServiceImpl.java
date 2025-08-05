package com.diegoygabriela.backend_novalink.service.impl;
import com.diegoygabriela.backend_novalink.entity.Cita;
import com.diegoygabriela.backend_novalink.repository.CitaRepository;
import com.diegoygabriela.backend_novalink.service.Inter.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class  CitaServiceImpl implements CitaService {
    @Autowired
    private CitaRepository citaRepository;

    @Override
    public void insert(Cita cita) {
        citaRepository.save(cita);
    }

    @Override
    public List<Cita> list() {
        return citaRepository.findAll();
    }

    @Override
    public void delete(int idCita) {
        citaRepository.deleteById((long) idCita);
    }

    @Override
    public Cita listId(int idCita) {
        return citaRepository.findById((long) idCita).orElse(new Cita());
    }

}