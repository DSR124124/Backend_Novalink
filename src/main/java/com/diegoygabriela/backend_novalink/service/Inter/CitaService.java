package com.diegoygabriela.backend_novalink.service.Inter;
import com.diegoygabriela.backend_novalink.entity.Cita;

import java.util.List;

public interface CitaService {


    public void insert(Cita cita);
    public List<Cita> list();
    public void delete(int idCita);
    public Cita listId(int idCita);

}
