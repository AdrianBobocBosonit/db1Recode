package com.bosonit.db1Recode.persona.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.persona.application.PersonaService;
import com.bosonit.db1Recode.persona.infraestructure.controller.input.PersonaInputDTO;
import com.bosonit.db1Recode.persona.infraestructure.controller.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class PersonaCreateController {

    @Autowired
    PersonaService personaService;

    @PostMapping
    public PersonaOutputDTO createPersona(@RequestBody PersonaInputDTO personaInputDTO) throws UnprocessableEntityException {
        return personaService.createPersona(personaInputDTO);
    }

}


