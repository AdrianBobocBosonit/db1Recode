package com.bosonit.db1Recode.student.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.student.application.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentDeleteController {

    @Autowired
    StudentService studentService;

    @DeleteMapping("/{id_student}")
    public String deleteStudentById(@PathVariable String id_student) throws EntityNotFoundException {
        studentService.deleteStudentById(id_student);
        return "El estudiante con el id " + id_student + " ha sido eliminado correctamente";
    }

    @DeleteMapping("/persona/{id_persona}")
    public String deleteStudentByPersona(@PathVariable String id_persona) throws EntityNotFoundException {
        studentService.deleteStudentByPersona(id_persona);
        return "El estudiante con el id persona " + id_persona + " ha sido eliminado correctamente";
    }
}
