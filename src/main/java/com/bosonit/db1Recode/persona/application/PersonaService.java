package com.bosonit.db1Recode.persona.application;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.persona.domain.Persona;
import com.bosonit.db1Recode.persona.infraestructure.controller.input.PersonaInputDTO;
import com.bosonit.db1Recode.persona.infraestructure.controller.output.PersonaOutputDTO;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    PersonaOutputDTO createPersona(PersonaInputDTO personaInputDTO) throws UnprocessableEntityException;

    List<PersonaOutputDTO> findAllPersonas() throws EntityNotFoundException;

    Optional<Persona> findPersonaById(String idPersona) throws EntityNotFoundException;

    List<PersonaOutputDTO> findAllPersonaByUsername(String username) throws EntityNotFoundException;

    PersonaOutputDTO updatePersona(String idPersona, PersonaInputDTO personaInputDTO) throws EntityNotFoundException, UnprocessableEntityException;

    void deletePersona(String idPersona) throws EntityNotFoundException;

}
