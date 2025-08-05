package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Evento;

import java.util.List;

public interface EventoService {

    public void insert(Evento evento);
    public List<Evento> list();
    public void delete(int idEvento);
    public Evento listId(int idEvento);

}