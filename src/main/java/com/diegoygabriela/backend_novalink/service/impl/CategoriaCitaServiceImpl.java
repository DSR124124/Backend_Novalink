package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.CategoriaCita;
import com.diegoygabriela.backend_novalink.repository.CategoriaCitaRepository;
import com.diegoygabriela.backend_novalink.service.Inter.CategoriaCitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaCitaServiceImpl implements CategoriaCitaService {

    private final CategoriaCitaRepository repository;

    @Override
    public CategoriaCita insert(CategoriaCita categoria) {
        return repository.save(categoria);
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
        return repository.findById(idCategoria).orElse(null);
    }

    @Override
    public CategoriaCita update(CategoriaCita categoria) {
        return repository.save(categoria);
    }

    @Override
    public boolean canDelete(Long idCategoria) {
        return countCitasByCategoria(idCategoria) == 0;
    }

    @Override
    public long countCitasByCategoria(Long idCategoria) {
        return repository.countCitasByCategoria(idCategoria);
    }
}

