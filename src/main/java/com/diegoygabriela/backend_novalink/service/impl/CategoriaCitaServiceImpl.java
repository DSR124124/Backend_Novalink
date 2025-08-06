package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.CategoriaCita;
import com.diegoygabriela.backend_novalink.repository.CategoriaCitaRepository;
import com.diegoygabriela.backend_novalink.service.Inter.CategoriaCitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaCitaServiceImpl implements CategoriaCitaService {

    private final CategoriaCitaRepository repository;

    @Override
    public void insert(CategoriaCita categoria) {
        repository.save(categoria);
    }

    @Override
    public List<CategoriaCita> list() {
        return repository.findAll();
    }

    @Override
    public void delete(Long idCategoria) {
        repository.deleteById(idCategoria);
    }

    @Override
    public CategoriaCita listId(Long idCategoria) {
        Optional<CategoriaCita> categoria = repository.findById(idCategoria);
        return categoria.orElse(null);
    }
}

