package com.bosonit.db1Recode.persona.application;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.persona.domain.Persona;
import com.bosonit.db1Recode.persona.infraestructure.controller.input.PersonaInputDTO;
import com.bosonit.db1Recode.persona.infraestructure.controller.output.PersonaOutputDTO;
import com.bosonit.db1Recode.persona.infraestructure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDTO createPersona(PersonaInputDTO personaInputDTO) throws UnprocessableEntityException {

        if (!personaInputDTO.getCompany_email().contains("@")) {
            throw new UnprocessableEntityException("EL FORMATO DEL EMAIL CORPORATIVO ES INCORRECTO", 422);
        }
        if (!personaInputDTO.getPersonal_email().contains("@")) {
            throw new UnprocessableEntityException("EL FORMATO DEL EMAIL PERSONAL ES INCORRECTO", 422);
        }

        Persona persona = personaInputDTO.personaInputDTO();

        personaRepository.save(persona);

        return new PersonaOutputDTO(persona);
    }

    @Override
    public List<PersonaOutputDTO> findAllPersonas() throws EntityNotFoundException {

        List<Persona> personaList = personaRepository.findAll();

        if (personaList.isEmpty()) {
            throw new EntityNotFoundException("NO HAY NINGUNA PERSONA CREADA AUN", 404);
        }

        return personaList.stream().map(persona -> new PersonaOutputDTO(persona)).collect(Collectors.toList());


    }

    @Override
    public Optional<Persona> findPersonaById(String id_persona) throws EntityNotFoundException {
        System.err.println("EL ID DE LA PERSONA EN LA IMPL DE PERSONA ES: " + id_persona);
        Optional<Persona> personaOptional = personaRepository.findById(id_persona);

        if (personaOptional != null) {
            return personaOptional;
        } else {
            throw new EntityNotFoundException("LA PERSONA A BUSCAR NO EXISTE", 404);
        }

    }

    @Override
    public List<PersonaOutputDTO> findAllPersonaByUsername(String username) throws EntityNotFoundException {

        List<Persona> personaList = personaRepository.findByUsername(username);

        if (personaList.isEmpty()) {
            throw new EntityNotFoundException("NO HAY NINGUNA PERSONA CON ESE USERNAME", 404);
        }

        return personaList.stream().map(persona -> new PersonaOutputDTO(persona)).collect(Collectors.toList());


    }

    @Override
    public PersonaOutputDTO updatePersona(String idPersona, PersonaInputDTO personaInputDTO) throws EntityNotFoundException, UnprocessableEntityException  {

        Persona persona = personaRepository.findById(idPersona).orElse(null);

        if (persona == null) {
            throw new EntityNotFoundException("LA PERSONA A ACTUALIZAR NO EXISTE", 404);
        }

        if (personaInputDTO.getUsername() == null) {
            throw new UnprocessableEntityException("EL USERNAME NO PUEDE SER NULO", 422);
        }
        if ((personaInputDTO.getUsername().length()>10) || (personaInputDTO.getUsername().length()<6)) {
            throw new UnprocessableEntityException("EL USERNAME ES DEMASIADO CORTO", 422);
        }
        if (personaInputDTO.getPassword() == null) {
            throw new UnprocessableEntityException("LA PASSWORD NO PUEDE SER NULA", 422);
        }
        if (personaInputDTO.getName() == null) {
            throw new UnprocessableEntityException("EL NAME NO PUEDE SER NULO", 422);
        }
        if (personaInputDTO.getCompany_email() == null) {
            throw new UnprocessableEntityException("EL EMAIL CORPORATIVO NO PUEDE SER NULO", 422);
        }
        if (!personaInputDTO.getCompany_email().contains("@")) {
            throw new UnprocessableEntityException("EL FORMATO DEL EMAIL CORPORATIVO ES INCORRECTO", 422);
        }
        if (personaInputDTO.getPersonal_email() == null) {
            throw new UnprocessableEntityException("EL EMAIL PERSONAL NO PUEDER SER NULO", 422);
        }
        if (!personaInputDTO.getPersonal_email().contains("@")) {
            throw new UnprocessableEntityException("EL FORMATO DEL EMAIL PERSONAL ES INCORRECTO", 422);
        }
        if (personaInputDTO.getCity() == null) {
            throw new UnprocessableEntityException("LA CITY NO PUEDE SER NULA", 422);
        }
        if (personaInputDTO.getActive() == null) {
            throw new UnprocessableEntityException("ESTAR ACTIVO O NO NO PUEDE SER NULO", 422);
        }
        if (personaInputDTO.getCreated_date() == null) {
            throw new UnprocessableEntityException("SE HA DE ADJUNTAR LA FECHA CREADA", 422);
        }

        persona.setUsername(personaInputDTO.getUsername());
        persona.setPassword(personaInputDTO.getPassword());
        persona.setName(personaInputDTO.getName());
        persona.setSurname(personaInputDTO.getSurname());
        persona.setCompany_email(personaInputDTO.getCompany_email());
        persona.setPersonal_email(personaInputDTO.getPersonal_email());
        persona.setCity(personaInputDTO.getCity());
        persona.setActive(personaInputDTO.getActive());
        persona.setCreated_date(personaInputDTO.getCreated_date());
        persona.setImagen_url(personaInputDTO.getImagen_url());
        persona.setTermination_date(personaInputDTO.getTermination_date());

        personaRepository.save(persona);

        return new PersonaOutputDTO(persona);
    }

    @Override
    public void deletePersona(String idPersona) throws EntityNotFoundException {

        Persona persona = personaRepository.findById(idPersona).orElse(null);

        if (persona == null) {
            throw new EntityNotFoundException("LA PERSONA A ELIMINAR NO EXISTE", 404);
        }

        personaRepository.delete(persona);
    }
}
