package com.bosonit.db1Recode.profesor.application;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.persona.domain.Persona;
import com.bosonit.db1Recode.persona.infraestructure.repository.PersonaRepository;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import com.bosonit.db1Recode.profesor.infraestructure.controller.input.ProfesorInputDTO;
import com.bosonit.db1Recode.profesor.infraestructure.controller.output.ProfesorOutputDTO;
import com.bosonit.db1Recode.profesor.infraestructure.repository.ProfesorRepository;
import com.bosonit.db1Recode.student.infraestructure.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements ProfesorService{

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Override
    public ProfesorOutputDTO createProfesor(ProfesorInputDTO profesorInputDTO) throws UnprocessableEntityException, EntityNotFoundException {

        if (profesorInputDTO.getId_persona() == null) {
            throw new UnprocessableEntityException("EL ID PERSONA NO PUEDE SER NULO", 422);
        }
        if (profesorInputDTO.getBranch() == null) {
            throw new UnprocessableEntityException("LA BRANCH NO PUEDE SER NULA", 422);
        }

        Persona persona = personaRepository.findById(profesorInputDTO.getId_persona()).orElse(null);

        if (persona == null) {
            throw new EntityNotFoundException("LA PERSONA SELECCIONADA NO EXISTE", 404);
        }

        if ((studentRepository.findStudentByPersona(persona).isPresent()) || (profesorRepository.findProfesorByPersona(persona).isPresent())) {
            throw new UnprocessableEntityException("LA PERSONA YA ES UN ESTUDIANTE O UN PROFESOR", 422);
        }


        return new ProfesorOutputDTO(profesorRepository.save(profesorInputDTO.profesorInputDtop(persona)));
    }

    @Override
    public List<ProfesorOutputDTO> findAllProfesores() throws EntityNotFoundException {

        List<Profesor> profesorList = profesorRepository.findAll();

        if (profesorList.isEmpty()) {
            throw new EntityNotFoundException("NO HAY NINGUN PROFESOR ASIGNADO AUN", 404);
        }

        return profesorList.stream().map(profesor -> new ProfesorOutputDTO(profesor)).collect(Collectors.toList());
    }

    @Override
    public Optional<Profesor> findProfesorById(String id_profesor) throws EntityNotFoundException {

        Optional<Profesor> profesor = profesorRepository.findById(id_profesor);

        if (profesor == null) {
            throw new EntityNotFoundException("EL PROFESOR CON EL ID SELECCIONADO NO EXISTE", 404);
        }

        return profesor;
    }

    @Override
    public Optional<Profesor> findProfesorByPersona(String id_persona) throws EntityNotFoundException {

        Persona persona = personaRepository.findById(id_persona).orElse(null);

        if (persona == null) {
            throw new EntityNotFoundException("EL ID ENVIADO NO EXISTE COMO PERSONA", 404);
        }

        Optional<Profesor> profesor = profesorRepository.findProfesorByPersona(persona);

        if (profesor == null) {
            throw new EntityNotFoundException("ESTA PERSONA NO ES PROFESOR", 404);
        }

        return profesor;
    }

    @Override
    public void deleteProfesorById(String id_profesor) throws EntityNotFoundException {

        Profesor profesor = profesorRepository.findById(id_profesor).orElse(null);

        if (profesor == null) {
            throw new EntityNotFoundException("EL PROFESOR CON DICHO ID PROFESOR NO EXISTE", 404);
        }

        profesorRepository.delete(profesor);
    }

    @Override
    public void deleteProfesorByPersona(String id_persona) throws EntityNotFoundException {
        Persona persona = personaRepository.findById(id_persona).orElse(null);

        if (persona == null) {
            throw new EntityNotFoundException("LA PERSONA CON DICHO ID NO ES PROFESORA O NO ESTA DADA DE ALTA", 404);
        }

        Profesor profesor = profesorRepository.findProfesorByPersona(persona).orElse(null);

        if (profesor == null) {
            throw new EntityNotFoundException("DICHA PERSONA NO ES UN PROFESOR O NO ESTA DADO DE ALTA", 404);
        }

        profesorRepository.delete(profesor);
    }
}
