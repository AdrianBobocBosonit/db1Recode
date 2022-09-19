package com.bosonit.db1Recode.persona.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "PERSONA")
public class Persona {

    @Id
    @GeneratedValue(generator = "personaGenerator")
    @GenericGenerator(name = "personaGenerator",
            parameters = @Parameter(name = "prefijo", value = "persona"),
            strategy = "com.bosonit.db1Recode.MiGenerador"
    )
    private String id_persona;

    @Column(length = 10, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column
    private String surname;

    @Column(nullable = false)
    private String company_email;

    @Column(nullable = false)
    private String personal_email;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Date created_date;

    @Column
    private String imagen_url;

    @Column
    private Date termination_date;
}
