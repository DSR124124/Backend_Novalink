package com.diegoygabriela.backend_novalink.service.Inter;
import com.diegoygabriela.backend_novalink.entity.Role;

import java.util.List;

public interface RolService {
    void insert(Role rol);
    List<Role> list();
    void delete(Long idRol);
    Role listId(Long idRol);
}
