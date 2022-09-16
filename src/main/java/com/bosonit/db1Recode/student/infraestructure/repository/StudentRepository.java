package com.bosonit.db1Recode.student.infraestructure.repository;

import com.bosonit.db1Recode.student.domain.Student;
import com.bosonit.db1Recode.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findStudentByPersona(Persona persona);

}
