package com.bosonit.db1Recode.student.infraestructure.controller.output;

import com.bosonit.db1Recode.asignatura.domain.Asignatura;
import com.bosonit.db1Recode.persona.infraestructure.controller.output.PersonaOutputDTO;
import com.bosonit.db1Recode.profesor.infraestructure.controller.output.ProfesorOutputDTO;
import com.bosonit.db1Recode.student.domain.Student;
import lombok.Data;

import java.util.List;

@Data
public class StudentOutputDTO {
    private String id_student;
    private PersonaOutputDTO persona;
    private Integer numb_hours;
    private String comments;
    private ProfesorOutputDTO profesor;
    private String branch;
    private List<Asignatura> asignaturaList;

    public StudentOutputDTO(Student student) {
        setId_student(student.getId_student());
        setPersona(new PersonaOutputDTO(student.getPersona()));
        setNumb_hours(student.getNumb_hours());
        setComments(student.getComments());
        setProfesor(student.getProfesor() != null?new ProfesorOutputDTO(student.getProfesor()):null);
        setBranch(student.getBranch());
        setAsignaturaList(student.getAsignaturaList());
    }
}
