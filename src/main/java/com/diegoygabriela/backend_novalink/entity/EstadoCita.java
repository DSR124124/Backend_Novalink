package com.diegoygabriela.backend_novalink.entity;

public enum EstadoCita {
    PLANIFICADA("Planificada - Aún no ocurre"),
    EN_CURSO("En curso - Está sucediendo ahora"),
    COMPLETADA("Completada - Ya terminó"),
    CANCELADA("Cancelada - No se realizó"),
    REPROGRAMADA("Reprogramada - Se movió a otra fecha");

    private final String descripcion;

    EstadoCita(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Método para determinar el estado basado en la fecha
    public static EstadoCita determinarEstadoPorFecha(java.time.LocalDateTime fechaCita) {
        java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
        
        if (fechaCita.isAfter(ahora)) {
            return PLANIFICADA;
        } else if (fechaCita.isBefore(ahora.minusHours(2))) {
            return COMPLETADA; // Asume que duró máximo 2 horas
        } else {
            return EN_CURSO;
        }
    }
}
