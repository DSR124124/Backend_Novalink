package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Pareja;
import com.diegoygabriela.backend_novalink.repository.ParejaRepository;
import com.diegoygabriela.backend_novalink.service.Inter.ParejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParejaServiceImpl implements ParejaService {

    @Autowired
    private ParejaRepository repository;

    @Override
    public void insert(Pareja pareja) {
        repository.save(pareja);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Pareja> list() {
        return repository.findAll();
    }

    @Override
    public Pareja listId(Long id) {
        return repository.findById(id).orElse(new Pareja());
    }
}

