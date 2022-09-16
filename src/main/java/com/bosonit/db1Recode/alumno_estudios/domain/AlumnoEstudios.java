package com.bosonit.db1Recode.alumno_estudios.domain;

import com.bosonit.db1Recode.profesor.domain.Profesor;
import com.bosonit.db1Recode.student.domain.Student;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
//TODO cambiar el nombre de esto a asignatura
public class AlumnoEstudios {

    @Id
    @GeneratedValue(generator = "alumnoEstudiosGenerator")
    @GenericGenerator(name = "alumnoEstudiosGenerator",
            parameters = @Parameter(name = "prefijo", value = "alumnoEstudios"),
            strategy = "com.bosonit.db1Recode.MiGenerador"
    )
    private String id_estudio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_alumnoEstudios", joinColumns = @JoinColumn(name = "id_estudio"),
    inverseJoinColumns = @JoinColumn(name = "id_student"))
    private List<Student> students;

    @Column(name = "asignatura")
    private String asignatura;

    @Column(name = "comentarios")
    private String comment;

    @Column(name = "initial_date")
    private Date initial_date;

    @Column(name = "finish_date")
    private Date finish_date;
}
