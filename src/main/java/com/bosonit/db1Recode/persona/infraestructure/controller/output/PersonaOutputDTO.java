package com.bosonit.db1Recode.persona.infraestructure.controller.output;

import com.bosonit.db1Recode.persona.domain.Persona;
import lombok.Data;

import java.util.Date;

@Data
public class PersonaOutputDTO {
    private String person_id;
    private String username;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public PersonaOutputDTO(Persona persona) {
        this.person_id = persona.getId_persona();
        this.username = persona.getUsername();
        this.name = persona.getName();
        this.surname = persona.getSurname();
        this.company_email = persona.getCompany_email();
        this.personal_email= persona.getPersonal_email();
        this.city = persona.getCity();
        this.active = persona.getActive();
        this.created_date = persona.getCreated_date();
        this.imagen_url = persona.getImagen_url();
        this.termination_date = persona.getTermination_date();
    }
}
