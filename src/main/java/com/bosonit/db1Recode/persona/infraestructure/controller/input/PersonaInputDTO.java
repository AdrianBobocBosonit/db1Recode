package com.bosonit.db1Recode.persona.infraestructure.controller.input;

import com.bosonit.db1Recode.persona.domain.Persona;
import lombok.Data;

import java.util.Date;

@Data
public class PersonaInputDTO {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public Persona personaInputDTO() {
        Persona persona = new Persona();

        persona.setUsername(this.getUsername());
        persona.setPassword(this.getPassword());
        persona.setName(this.getName());
        persona.setSurname(this.getSurname());
        persona.setCompany_email(this.getCompany_email());
        persona.setPersonal_email(this.getPersonal_email());
        persona.setCity(this.getCity());
        persona.setActive(this.getActive());
        persona.setCreated_date(this.getCreated_date());
        persona.setImagen_url(this.getImagen_url());
        persona.setTermination_date(this.getTermination_date());

        return persona;
    }
}
