package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.DetalleRegalo;

import java.time.LocalDateTime;
import java.util.List;

public interface DetalleRegaloService {

    public void insert(DetalleRegalo detalleRegalo);
    public List<DetalleRegalo> list();
    public void delete(Long idDetalleRegalo);
    public DetalleRegalo listId(Long idDetalleRegalo);
    
    // Métodos adicionales para funcionalidad de regalos
    public List<DetalleRegalo> findByParejaId(Long parejaId);
    public List<DetalleRegalo> findByRemitenteId(Long remitenteId);
    public List<DetalleRegalo> findByReceptorId(Long receptorId);
    public List<DetalleRegalo> findRegalosBetweenUsers(Long usuario1Id, Long usuario2Id);
    public List<DetalleRegalo> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    public List<DetalleRegalo> findByCitaId(Long citaId);
    public List<DetalleRegalo> findByEventoId(Long eventoId);
    public Long countByParejaId(Long parejaId);
    public List<DetalleRegalo> findRecentRegalos(int dias);

}
