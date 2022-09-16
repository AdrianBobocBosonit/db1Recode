package com.bosonit.db1Recode.persona.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.persona.application.PersonaService;
import com.bosonit.db1Recode.persona.infraestructure.controller.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaFindController {

    @Autowired
    PersonaService personaService;

    @GetMapping
    public List<PersonaOutputDTO> findAllPersonas() throws EntityNotFoundException {
        return personaService.findAllPersonas();
    }

    @GetMapping("/{idPersona}")
    public PersonaOutputDTO findPersonaById(@PathVariable String idPersona) throws EntityNotFoundException {
        return new PersonaOutputDTO(personaService.findPersonaById(idPersona).get());
    }

    @GetMapping("/username/{username}")
    public List<PersonaOutputDTO> findAllPersonaByUsername(@PathVariable String username) throws EntityNotFoundException {
        return personaService.findAllPersonaByUsername(username);
    }

}
