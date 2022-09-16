package com.bosonit.db1Recode.student.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.student.application.StudentService;
import com.bosonit.db1Recode.student.infraestructure.controller.output.StudentOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentFindController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public List<StudentOutputDTO> findAllStudents() throws EntityNotFoundException {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id_student}")
    public StudentOutputDTO findStudentById(@PathVariable String id_student) throws EntityNotFoundException {
        return studentService.findStudentById(id_student);
    }

    @GetMapping("/persona/{id_persona}")
    public StudentOutputDTO findStudentByPersona(@PathVariable String id_persona) throws EntityNotFoundException {
        return studentService.findStudentByPersona(id_persona);
    }
}
