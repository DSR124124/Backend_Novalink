package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Nota;

import java.util.List;

public interface NotaService {

    public void insert(Nota nota);
    public List<Nota> list();
    public void delete(int idNota);
    public Nota listId(int idNota);

}