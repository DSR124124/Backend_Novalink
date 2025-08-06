package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.MultimediaEvento;
import com.diegoygabriela.backend_novalink.repository.MultimediaEventoRepository;
import com.diegoygabriela.backend_novalink.service.Inter.MultimediaEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultimediaEventoServiceImpl implements MultimediaEventoService {

    @Autowired
    private MultimediaEventoRepository repository;

    @Override
    public void insert(MultimediaEvento multimediaEvento) {
        repository.save(multimediaEvento);
    }

    @Override
    public List<MultimediaEvento> list() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public MultimediaEvento listId(Long id) {
        return repository.findById(id).orElse(new MultimediaEvento());
    }
}
