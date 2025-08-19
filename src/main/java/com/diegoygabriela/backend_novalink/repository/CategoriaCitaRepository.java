package com.diegoygabriela.backend_novalink.repository;

import com.diegoygabriela.backend_novalink.entity.CategoriaCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaCitaRepository extends JpaRepository<CategoriaCita, Long> {
}
