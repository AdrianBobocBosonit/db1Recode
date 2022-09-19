package com.bosonit.db1Recode.profesor.domain;

import com.bosonit.db1Recode.persona.domain.Persona;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "PROFESOR")
public class Profesor implements Serializable {
    @Id
    @GeneratedValue(generator = "profesorGenerator")
    @GenericGenerator(name = "profesorGenerator",
            parameters = @Parameter(name = "prefijo", value = "profesor"),
            strategy = "com.bosonit.db1Recode.MiGenerador"
    )
    private String id_profesor;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    private String comments;

    @Column(nullable = false)
    private String branch;
}
