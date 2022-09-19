package com.bosonit.db1Recode.asignatura.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.asignatura.application.AsignaturaService;
import com.bosonit.db1Recode.asignatura.infraestructure.controller.output.AsignaturaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/asignaturas")
public class AsignaturaFindController {

    @Autowired
    AsignaturaService asignaturaService;

    @GetMapping
    public List<AsignaturaOutputDTO> getAllAsignaturas() throws EntityNotFoundException {
        return asignaturaService.getAllAsignaturas();
    }
}
