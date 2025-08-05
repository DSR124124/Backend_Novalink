package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.DetalleRegalo;

import java.util.List;

public interface DetalleRegaloService {

    public void insert(DetalleRegalo detalleRegalo);
    public List<DetalleRegalo> list();
    public void delete(int idDetalleRegalo);
    public DetalleRegalo listId(int idDetalleRegalo);

}