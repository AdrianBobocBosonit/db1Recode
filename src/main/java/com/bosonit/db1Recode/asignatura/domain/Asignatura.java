package com.bosonit.db1Recode.asignatura.domain;

import com.bosonit.db1Recode.profesor.domain.Profesor;
import com.bosonit.db1Recode.student.domain.Student;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ASIGNATURA")
public class Asignatura implements Serializable {

    @Id
    @GeneratedValue(generator = "asignaturaGenerator")
    @GenericGenerator(name = "asignaturaGenerator",
            parameters = @Parameter(name = "prefijo", value = "asignaturaGenerator"),
            strategy = "com.bosonit.db1Recode.MiGenerador")
    @Column(nullable = false)
    private String id_asignatura;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_asignatura", joinColumns = @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura"),
    inverseJoinColumns = @JoinColumn(name = "id_student", referencedColumnName = "id_student"))
    private List<Student> students;

    @Column(name = "asignatura", nullable = false)
    private String nombreAsignatura;

    @Column(name = "comentarios")
    private String comment;

    @Column(name = "initial_date")
    private Date initial_date;

    @Column(name = "finish_date")
    private Date finish_date;
}
