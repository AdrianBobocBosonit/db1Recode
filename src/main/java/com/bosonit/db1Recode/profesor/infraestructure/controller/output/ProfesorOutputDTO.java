package com.bosonit.db1Recode.profesor.infraestructure.controller.output;

import com.bosonit.db1Recode.persona.infraestructure.controller.output.PersonaOutputDTO;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import lombok.Data;

@Data
public class ProfesorOutputDTO {

    private String id_profesor;
    private PersonaOutputDTO personaOutputDTO;
    private String comments;
    private String branch;

    public ProfesorOutputDTO(Profesor profesor) {
        setId_profesor(profesor.getId_profesor());
        setPersonaOutputDTO(new PersonaOutputDTO(profesor.getPersona()));
        setComments(profesor.getComments());
        setBranch(profesor.getBranch());
    }

}
