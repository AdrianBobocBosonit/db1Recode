package com.bosonit.db1Recode.asignatura.infraestructure.controller.output;

import com.bosonit.db1Recode.asignatura.domain.Asignatura;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import com.bosonit.db1Recode.student.infraestructure.controller.output.StudentOutputDTONoAsignaturas;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AsignaturaOutputDTO {

    private String id_asignatura;
    private Profesor profesor;
    private List<StudentOutputDTONoAsignaturas> studentList;
    private String nombreAsignatura;
    private String comment;
    private Date initial_date;
    private Date finish_date;

    public AsignaturaOutputDTO(Asignatura asignatura) {
        setId_asignatura(asignatura.getId_asignatura());
        setProfesor(asignatura.getProfesor());
        setStudentList(asignatura.getStudents() != null?asignatura.getStudents().stream().map(StudentOutputDTONoAsignaturas::new).collect(Collectors.toList()):null);
        setNombreAsignatura(asignatura.getNombreAsignatura());
        setComment(asignatura.getComment());
        setInitial_date(asignatura.getInitial_date());
        setFinish_date(asignatura.getFinish_date());
    }
}
