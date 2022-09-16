package com.bosonit.db1Recode.profesor.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.profesor.application.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profesores")
public class ProfesorDeleteController {

    @Autowired
    ProfesorService profesorService;

    @DeleteMapping("/{id_profesor}")
    public String deleteProfesorById(@PathVariable String id_profesor) throws EntityNotFoundException {
        profesorService.deleteProfesorById(id_profesor);
        return "El profesor con el id profesor " + id_profesor + " ha sido eliminado con exito";
    }

    @DeleteMapping("/persona/{id_persona}")
    public String deleteProfesorByPersona(@PathVariable String id_persona) throws EntityNotFoundException {
        profesorService.deleteProfesorByPersona(id_persona);
        return "La persona con el id persona: " + id_persona + " ha sido eliminado con exito";
    }
}
