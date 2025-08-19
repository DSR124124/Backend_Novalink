package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Multimedia;

import java.util.List;

public interface MultimediaService {

    public void insert(Multimedia multimedia);
    public List<Multimedia> list();
    public void delete(Long idMultimedia);
    public Multimedia listId(Long idMultimedia);

}
