package com.bosonit.db1Recode.profesor.application;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import com.bosonit.db1Recode.profesor.infraestructure.controller.input.ProfesorInputDTO;
import com.bosonit.db1Recode.profesor.infraestructure.controller.output.ProfesorOutputDTO;

import java.util.List;
import java.util.Optional;

public interface ProfesorService {

    ProfesorOutputDTO createProfesor(ProfesorInputDTO profesorInputDTO) throws UnprocessableEntityException, EntityNotFoundException;

    List<ProfesorOutputDTO> findAllProfesores() throws EntityNotFoundException;

    Optional<Profesor> findProfesorById(String id_profesor) throws EntityNotFoundException;

    Optional<Profesor> findProfesorByPersona(String id_persona) throws EntityNotFoundException;

    void deleteProfesorById(String id_profesor) throws EntityNotFoundException;

    void deleteProfesorByPersona(String id_persona) throws EntityNotFoundException;
}
