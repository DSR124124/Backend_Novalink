package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Multimedia;
import com.diegoygabriela.backend_novalink.repository.MultimediaRepository;
import com.diegoygabriela.backend_novalink.service.Inter.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultimediaServiceImpl implements MultimediaService {
    @Autowired
    private MultimediaRepository multimediaRepository;

    @Override
    public void insert(Multimedia multimedia) {
        multimediaRepository.save(multimedia);
    }

    @Override
    public List<Multimedia> list() {
        return multimediaRepository.findAll();
    }

    @Override
    public void delete(Long idMultimedia) {
        multimediaRepository.deleteById(idMultimedia);
    }

    @Override
    public Multimedia listId(Long idMultimedia) {
        return multimediaRepository.findById(idMultimedia).orElse(new Multimedia());
    }
}