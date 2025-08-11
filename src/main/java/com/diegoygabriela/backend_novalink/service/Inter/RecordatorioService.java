package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Recordatorio;
import com.diegoygabriela.backend_novalink.entity.Recordatorio.TipoRecordatorio;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordatorioService {
    
    // Métodos CRUD básicos
    void insert(Recordatorio recordatorio);
    List<Recordatorio> list();
    void delete(Long id);
    Recordatorio listId(Long id);
    
    // Métodos específicos para recordatorios
    List<Recordatorio> findByParejaId(Long parejaId);
    List<Recordatorio> findActivosByParejaId(Long parejaId);
    List<Recordatorio> findByTipo(TipoRecordatorio tipo);
    List<Recordatorio> findByParejaIdAndTipo(Long parejaId, TipoRecordatorio tipo);
    
    // Métodos para sistema de notificaciones
    List<Recordatorio> findProximosRecordatorios(Long parejaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<Recordatorio> findRecordatoriosParaNotificar(LocalDateTime fechaNotificacion);
    
    // Métodos para recordatorios recurrentes
    List<Recordatorio> findRecordatoriosRecurrentes();
    List<Recordatorio> findByParejaIdAndFechaRange(Long parejaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    // Métodos de estadísticas
    Long countActivosByParejaId(Long parejaId);
    
    // Métodos de gestión de estado
    void marcarComoCompletado(Long recordatorioId);
    void marcarComoCancelado(Long recordatorioId);
    void reactivarRecordatorio(Long recordatorioId);
}
