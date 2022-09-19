package com.bosonit.db1Recode.asignatura.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.asignatura.application.AsignaturaService;
import com.bosonit.db1Recode.asignatura.infraestructure.controller.input.AsignaturaInputDTO;
import com.bosonit.db1Recode.asignatura.infraestructure.controller.output.AsignaturaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asignaturas")
public class AsignaturaCreateController {

    @Autowired
    AsignaturaService asignaturaService;

    @PostMapping
    AsignaturaOutputDTO addAsignatura(@RequestBody AsignaturaInputDTO asignaturaInputDTO) throws EntityNotFoundException, UnprocessableEntityException {
        System.err.println("ESTO ES LO QUE RECIBIMOS EN ADDASIGNATURA: " + asignaturaInputDTO);
        return asignaturaService.addAsignatura(asignaturaInputDTO);
    }

}
