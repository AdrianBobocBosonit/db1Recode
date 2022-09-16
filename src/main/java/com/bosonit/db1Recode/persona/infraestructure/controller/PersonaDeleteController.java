package com.bosonit.db1Recode.persona.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.persona.application.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class PersonaDeleteController {

    @Autowired
    PersonaService personaService;

    @DeleteMapping("/{idPersona}")
    public String deletePersona(@PathVariable String idPersona) throws EntityNotFoundException {
        personaService.deletePersona(idPersona);
        return "LA PERSONA " + idPersona + " HA SIDO BORRADA CORRECTAMENTE";
    }

}
