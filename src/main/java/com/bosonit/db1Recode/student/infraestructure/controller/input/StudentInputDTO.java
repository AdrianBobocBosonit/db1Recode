package com.bosonit.db1Recode.student.infraestructure.controller.input;

import com.bosonit.db1Recode.persona.domain.Persona;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import com.bosonit.db1Recode.student.domain.Student;
import lombok.Data;

@Data
public class StudentInputDTO {
    private String id_persona;
    private Persona persona;
    private Integer numb_hours;
    private String comments;
    private String id_profesor;
    private Profesor profesor;
    private String branch;

    public Student studentInputDto(Persona persona, Profesor profesor) {
        Student student = new Student();

        student.setPersona(persona);
        student.setNumb_hours(this.getNumb_hours());
        student.setComments(this.getComments());
        student.setProfesor(profesor);
        student.setBranch(this.getBranch());

        return student;
    }
}
