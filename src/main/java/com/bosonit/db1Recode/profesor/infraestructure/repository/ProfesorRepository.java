package com.bosonit.db1Recode.profesor.infraestructure.repository;

import com.bosonit.db1Recode.persona.domain.Persona;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    Optional<Profesor> findProfesorByPersona(Persona persona);
}
