package com.bosonit.db1Recode.asignatura.application;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.asignatura.domain.Asignatura;
import com.bosonit.db1Recode.asignatura.infraestructure.controller.input.AsignaturaInputDTO;
import com.bosonit.db1Recode.asignatura.infraestructure.controller.output.AsignaturaOutputDTO;
import com.bosonit.db1Recode.asignatura.infraestructure.repository.AsignaturaRepository;
import com.bosonit.db1Recode.profesor.application.ProfesorService;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    ProfesorService profesorService;

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Override
    public AsignaturaOutputDTO addAsignatura(AsignaturaInputDTO asignaturaInputDTO) throws EntityNotFoundException, UnprocessableEntityException {

        if (asignaturaInputDTO.getId_profesor() == null) {
            throw new EntityNotFoundException("EL PROFESOR DE LA IDPROFESOR NO PUEDE SER NULO",404);
        }
        if (asignaturaInputDTO.getNombreAsignatura() == null) {
            throw new UnprocessableEntityException("LA ASIGNATURA NECESITA TENER NOMBRE", 422);
        }

        Profesor profesor = profesorService.findProfesorById(asignaturaInputDTO.getId_profesor()).orElse(null);

        if (profesor == null) {
            throw new EntityNotFoundException("EL PROFESOR CON EL IDPROFESOR ENVIADO NO EXISTE", 404);
        }

        Asignatura asignatura = asignaturaInputDTO.asignaturaInputDTO(profesor);

        return new AsignaturaOutputDTO(asignaturaRepository.save(asignatura));
    }

    @Override
    public List<AsignaturaOutputDTO> getAllAsignaturas() throws EntityNotFoundException {
        List<AsignaturaOutputDTO> asignaturaOutputDTOList = asignaturaRepository.findAll().stream().map(AsignaturaOutputDTO::new).collect(Collectors.toList());

        if (asignaturaOutputDTOList.isEmpty()) {
            throw new EntityNotFoundException("LA LISTA ESTA VACIA, NO HAY NINGUNA ASIGNATURA CREADA", 404);
        }

        return asignaturaOutputDTOList;
    }
}
