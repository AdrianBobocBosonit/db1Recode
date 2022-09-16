package com.bosonit.db1Recode.student.infraestructure.controller;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.student.application.StudentService;
import com.bosonit.db1Recode.student.infraestructure.controller.input.StudentInputDTO;
import com.bosonit.db1Recode.student.infraestructure.controller.output.StudentOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentUpdateController {

    @Autowired
    StudentService studentService;

    @PostMapping("/{id_student}")
    public StudentOutputDTO updateStudentById(@PathVariable String id_student,@RequestBody StudentInputDTO studentInputDTO) throws EntityNotFoundException {
        return studentService.updateStudentById(id_student, studentInputDTO);
    }

}
