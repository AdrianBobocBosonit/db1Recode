package com.bosonit.db1Recode.asignatura.application;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.asignatura.infraestructure.controller.input.AsignaturaInputDTO;
import com.bosonit.db1Recode.asignatura.infraestructure.controller.output.AsignaturaOutputDTO;

import java.util.List;

public interface AsignaturaService {

    AsignaturaOutputDTO addAsignatura(AsignaturaInputDTO asignaturaInputDTO) throws EntityNotFoundException, UnprocessableEntityException;

    List<AsignaturaOutputDTO> getAllAsignaturas() throws EntityNotFoundException;

}
