package com.diegoygabriela.backend_novalink.service.Inter;
import com.diegoygabriela.backend_novalink.entity.Rol;

import java.util.List;

public interface RolService {
    void insert(Rol rol);
    List<Rol> list();
    void delete(Long idRol);
    Rol listId(Long idRol);
}
