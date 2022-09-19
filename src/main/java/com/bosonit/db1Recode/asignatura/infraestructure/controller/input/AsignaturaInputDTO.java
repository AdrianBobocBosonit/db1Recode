package com.bosonit.db1Recode.asignatura.infraestructure.controller.input;

import com.bosonit.db1Recode.asignatura.domain.Asignatura;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import lombok.Data;

import java.util.Date;

@Data
public class AsignaturaInputDTO {

    private String id_profesor;
    private String nombreAsignatura;
    private String comment;
    private Date initial_date;
    private Date finish_date;

    public Asignatura asignaturaInputDTO(Profesor profesor) {
        Asignatura asignarura = new Asignatura();
        asignarura.setProfesor(profesor);
        asignarura.setNombreAsignatura(this.getNombreAsignatura());
        asignarura.setComment(this.getComment());
        asignarura.setInitial_date(this.getInitial_date());
        asignarura.setFinish_date(this.getFinish_date());
        return asignarura;
    }

}
