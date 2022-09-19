package com.bosonit.db1Recode.student.application;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.student.infraestructure.controller.input.StudentInputDTO;
import com.bosonit.db1Recode.student.infraestructure.controller.output.StudentOutputDTO;

import java.util.List;

public interface StudentService {

    StudentOutputDTO createStudent(StudentInputDTO studentInputDTO) throws UnprocessableEntityException, EntityNotFoundException;

    List<StudentOutputDTO> findAllStudents() throws EntityNotFoundException;

    StudentOutputDTO findStudentById(String id_student) throws EntityNotFoundException;

    StudentOutputDTO findStudentByPersona(String id_persona) throws EntityNotFoundException;

    void deleteStudentById(String id_student) throws EntityNotFoundException;

    void deleteStudentByPersona(String id_persona) throws EntityNotFoundException;

    StudentOutputDTO updateStudentById(String id_student, StudentInputDTO studentInputDTO) throws EntityNotFoundException;

    String setAsignatura(String id_student, List<String> id_asignaturas) throws EntityNotFoundException;
}
