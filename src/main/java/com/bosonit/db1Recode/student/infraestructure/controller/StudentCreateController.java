package com.bosonit.db1Recode.student.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.student.application.StudentService;
import com.bosonit.db1Recode.student.infraestructure.controller.input.StudentInputDTO;
import com.bosonit.db1Recode.student.infraestructure.controller.output.StudentOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentCreateController {

    @Autowired
    StudentService studentService;

    @PostMapping
    public StudentOutputDTO createStudent(@RequestBody StudentInputDTO studentInputDTO) throws UnprocessableEntityException, EntityNotFoundException {
        System.err.println("ESTOS SON LOS DATOS QUE ME LLEGA: " + studentInputDTO);
        return studentService.createStudent(studentInputDTO);
    }
}
