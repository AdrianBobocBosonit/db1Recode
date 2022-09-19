package com.bosonit.db1Recode.student.domain;

import com.bosonit.db1Recode.asignatura.domain.Asignatura;
import com.bosonit.db1Recode.persona.domain.Persona;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Student implements Serializable {
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @Column(nullable = false)
    private String branch;

    @ManyToMany(mappedBy = "students")
    List<Asignatura> asignaturaList;
}

/*
SELECT * FROM PERSONA;
SELECT * FROM PROFESOR;
SELECT * FROM STUDENT;
SELECT * FROM ASIGNATURA;
SELECT * FROM STUDENT_ASIGNATURA;
 */
