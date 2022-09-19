package com.bosonit.db1Recode.student.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.student.application.StudentService;
import com.bosonit.db1Recode.student.infraestructure.controller.input.StudentInputDTO;
import com.bosonit.db1Recode.student.infraestructure.controller.output.StudentOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentUpdateController {

    @Autowired
    StudentService studentService;

    @PutMapping("/{id_student}")
    public StudentOutputDTO updateStudentById(@PathVariable String id_student,@RequestBody StudentInputDTO studentInputDTO) throws EntityNotFoundException {
        return studentService.updateStudentById(id_student, studentInputDTO);
    }

    @PutMapping("/setAsignaturas/{id_student}")
    public String setAsignaturas(@PathVariable String id_student, @RequestBody List<String> id_asignaturas) throws EntityNotFoundException {
        System.err.println("ESTE ES EL ID STUDENT QUE LLEGA: " + id_student);
        System.err.println("ESTOS SON LOS ID_ASIGNATURAS QUE LLEGA: " + id_asignaturas);
        return studentService.setAsignatura(id_student, id_asignaturas);
    }

}
