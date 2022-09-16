package com.bosonit.db1Recode.persona.infraestructure.repository;

import com.bosonit.db1Recode.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {
    List<Persona> findByUsername(String username);
}
