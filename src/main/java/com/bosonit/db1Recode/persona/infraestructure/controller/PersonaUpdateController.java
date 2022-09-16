package com.bosonit.db1Recode.persona.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.persona.application.PersonaService;
import com.bosonit.db1Recode.persona.infraestructure.controller.input.PersonaInputDTO;
import com.bosonit.db1Recode.persona.infraestructure.controller.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personas")
public class PersonaUpdateController {

    @Autowired
    PersonaService personaService;

    @PutMapping("/{idPersona}")
    public PersonaOutputDTO updatePersona(@PathVariable String idPersona, @RequestBody PersonaInputDTO personaInputDTO) throws EntityNotFoundException, UnprocessableEntityException {
        return personaService.updatePersona(idPersona, personaInputDTO);
    }
}
