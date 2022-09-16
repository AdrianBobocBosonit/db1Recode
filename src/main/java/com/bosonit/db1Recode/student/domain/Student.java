package com.bosonit.db1Recode.student.domain;

import com.bosonit.db1Recode.alumno_estudios.domain.AlumnoEstudios;
import com.bosonit.db1Recode.persona.domain.Persona;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(generator = "studentGenerator")
    @GenericGenerator(name = "studentGenerator",
            parameters = @Parameter(name = "prefijo", value = "student"),
            strategy = "com.bosonit.db1Recode.MiGenerador"
    )
    private String id_student;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column(nullable = false)
    private Integer numb_hours;

    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @Column(nullable = false)
    private String branch;

    @ManyToMany(mappedBy = "students")
    List<AlumnoEstudios> alumnoEstudios;
}
