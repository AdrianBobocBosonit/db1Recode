package com.bosonit.db1Recode.profesor.infraestructure.controller.input;

import com.bosonit.db1Recode.persona.domain.Persona;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import lombok.Data;

@Data
public class ProfesorInputDTO {

    private String id_persona;
    private String comments;
    private String branch;

    public Profesor profesorInputDtop(Persona persona) {
        Profesor profesor = new Profesor();

        profesor.setPersona(persona);
        profesor.setComments(this.getComments());
        profesor.setBranch(this.getBranch());

        return profesor;
    }

}
