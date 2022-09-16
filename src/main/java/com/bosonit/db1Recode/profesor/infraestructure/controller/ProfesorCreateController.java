package com.bosonit.db1Recode.profesor.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.profesor.application.ProfesorService;
import com.bosonit.db1Recode.profesor.infraestructure.controller.input.ProfesorInputDTO;
import com.bosonit.db1Recode.profesor.infraestructure.controller.output.ProfesorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profesores")
public class ProfesorCreateController {

    @Autowired
    ProfesorService profesorService;

    @PostMapping
    public ProfesorOutputDTO createProfesor(@RequestBody ProfesorInputDTO profesorInputDTO) throws UnprocessableEntityException, EntityNotFoundException {
        return profesorService.createProfesor(profesorInputDTO);
    }
}
