package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Recordatorio;
import com.diegoygabriela.backend_novalink.entity.Recordatorio.EstadoRecordatorio;
import com.diegoygabriela.backend_novalink.entity.Recordatorio.TipoRecordatorio;
import com.diegoygabriela.backend_novalink.repository.RecordatorioRepository;
import com.diegoygabriela.backend_novalink.service.Inter.RecordatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecordatorioServiceImpl implements RecordatorioService {

    @Autowired
    private RecordatorioRepository recordatorioRepository;

    @Override
    public void insert(Recordatorio recordatorio) {
        recordatorioRepository.save(recordatorio);
    }

    @Override
    public List<Recordatorio> list() {
        return recordatorioRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        recordatorioRepository.deleteById(id);
    }

    @Override
    public Recordatorio listId(Long id) {
        Optional<Recordatorio> recordatorio = recordatorioRepository.findById(id);
        return recordatorio.orElse(null);
    }

    @Override
    public List<Recordatorio> findByParejaId(Long parejaId) {
        return recordatorioRepository.findByParejaId(parejaId);
    }

    @Override
    public List<Recordatorio> findActivosByParejaId(Long parejaId) {
        return recordatorioRepository.findByParejaIdAndEstado(parejaId, EstadoRecordatorio.ACTIVO);
    }

    @Override
    public List<Recordatorio> findByTipo(TipoRecordatorio tipo) {
        return recordatorioRepository.findByTipo(tipo);
    }

    @Override
    public List<Recordatorio> findByParejaIdAndTipo(Long parejaId, TipoRecordatorio tipo) {
        return recordatorioRepository.findByParejaIdAndTipo(parejaId, tipo);
    }

    @Override
    public List<Recordatorio> findProximosRecordatorios(Long parejaId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return recordatorioRepository.findProximosRecordatorios(parejaId, EstadoRecordatorio.ACTIVO, fechaInicio, fechaFin);
    }

    @Override
    public List<Recordatorio> findRecordatoriosParaNotificar(LocalDateTime fechaNotificacion) {
        return recordatorioRepository.findRecordatoriosParaNotificar(EstadoRecordatorio.ACTIVO, fechaNotificacion);
    }

    @Override
    public List<Recordatorio> findRecordatoriosRecurrentes() {
        return recordatorioRepository.findByEsRecurrente(true);
    }

    @Override
    public List<Recordatorio> findByParejaIdAndFechaRange(Long parejaId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return recordatorioRepository.findByParejaIdAndFechaRange(parejaId, fechaInicio, fechaFin);
    }

    @Override
    public Long countActivosByParejaId(Long parejaId) {
        return recordatorioRepository.countByParejaIdAndEstado(parejaId, EstadoRecordatorio.ACTIVO);
    }

    @Override
    public void marcarComoCompletado(Long recordatorioId) {
        Optional<Recordatorio> recordatorioOpt = recordatorioRepository.findById(recordatorioId);
        if (recordatorioOpt.isPresent()) {
            Recordatorio recordatorio = recordatorioOpt.get();
            recordatorio.setEstado(EstadoRecordatorio.COMPLETADO);
            recordatorioRepository.save(recordatorio);
        }
    }

    @Override
    public void marcarComoCancelado(Long recordatorioId) {
        Optional<Recordatorio> recordatorioOpt = recordatorioRepository.findById(recordatorioId);
        if (recordatorioOpt.isPresent()) {
            Recordatorio recordatorio = recordatorioOpt.get();
            recordatorio.setEstado(EstadoRecordatorio.CANCELADO);
            recordatorioRepository.save(recordatorio);
        }
    }

    @Override
    public void reactivarRecordatorio(Long recordatorioId) {
        Optional<Recordatorio> recordatorioOpt = recordatorioRepository.findById(recordatorioId);
        if (recordatorioOpt.isPresent()) {
            Recordatorio recordatorio = recordatorioOpt.get();
            recordatorio.setEstado(EstadoRecordatorio.ACTIVO);
            recordatorioRepository.save(recordatorio);
        }
    }
}
