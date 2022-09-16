package com.bosonit.db1Recode.profesor.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.profesor.application.ProfesorService;
import com.bosonit.db1Recode.profesor.infraestructure.controller.output.ProfesorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorFindController {

    @Autowired
    ProfesorService profesorService;

    @GetMapping
    public List<ProfesorOutputDTO> findAllProfesores() throws EntityNotFoundException {
        return profesorService.findAllProfesores();
    }

    @GetMapping("/{id_profesor}")
    public ProfesorOutputDTO findProfesorById(@PathVariable String id_profesor) throws EntityNotFoundException {
        System.err.println("ESTE ES EL ID QUE NOS LLEGA: " + id_profesor);
        return new ProfesorOutputDTO(profesorService.findProfesorById(id_profesor).get());
    }

    @GetMapping("/persona/{id_persona}")
    public ProfesorOutputDTO findProfesorByIdPersona(@PathVariable String id_persona) throws EntityNotFoundException {
        return new ProfesorOutputDTO(profesorService.findProfesorByPersona(id_persona).get());
    }
}
