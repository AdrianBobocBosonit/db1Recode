package com.bosonit.db1Recode.student.application;

import com.bosonit.db1Recode.Excepciones.EntityNotFoundException;
import com.bosonit.db1Recode.Excepciones.UnprocessableEntityException;
import com.bosonit.db1Recode.asignatura.domain.Asignatura;
import com.bosonit.db1Recode.asignatura.infraestructure.repository.AsignaturaRepository;
import com.bosonit.db1Recode.student.infraestructure.controller.input.StudentInputDTO;
import com.bosonit.db1Recode.student.infraestructure.controller.output.StudentOutputDTO;
import com.bosonit.db1Recode.persona.application.PersonaService;
import com.bosonit.db1Recode.persona.domain.Persona;
import com.bosonit.db1Recode.profesor.application.ProfesorService;
import com.bosonit.db1Recode.profesor.domain.Profesor;
import com.bosonit.db1Recode.profesor.infraestructure.repository.ProfesorRepository;
import com.bosonit.db1Recode.student.domain.Student;
import com.bosonit.db1Recode.student.infraestructure.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements  StudentService{

    @Autowired
    PersonaService personaService;

    @Autowired
    ProfesorService profesorService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Override
    public StudentOutputDTO createStudent(StudentInputDTO studentInputDTO) throws UnprocessableEntityException, EntityNotFoundException {

        if (studentInputDTO.getId_persona() == null) {
            throw new UnprocessableEntityException("EL ID DE LA PERSONA NO PUEDE SER NULO", 422);
        }
        if (studentInputDTO.getNumb_hours() == null) {
            throw new UnprocessableEntityException("EL NUMERO DE HORAS NO ESTA ESPECIFICADO Y HA DE ESTARLO", 422);
        }
        if (studentInputDTO.getBranch() == null) {
            throw new UnprocessableEntityException("EL BRANCH NO PUEDE DEJARSE A NULO", 422);
        }

        Optional<Persona> persona = personaService.findPersonaById(studentInputDTO.getId_persona());

        if (persona.isEmpty()) {
            throw new EntityNotFoundException("LA PERSONA CON ESE ID PERSONA NO EXISTE", 404);
        }

        Optional<Profesor> profesor = profesorService.findProfesorByPersona(persona.get().getId_persona());

        if (profesor.isPresent()) {
            throw new UnprocessableEntityException("ESA PERSONA YA ES UN PROFESOR", 422);
        }

        Optional<Student> student = studentRepository.findStudentByPersona(persona.get());

        if (student.isPresent()) {
            throw new UnprocessableEntityException("ESA PERSONA YA ES UN ESTUDIANTE", 422);
        }

        Optional<Profesor> setProfesorToStudent = profesorService.findProfesorById(studentInputDTO.getId_profesor());

        if (setProfesorToStudent.isEmpty()) {
            throw new EntityNotFoundException("NO EXISTE EL PROFESOR CON EL IDPROFESOR: " + studentInputDTO.getId_profesor(), 404);
        }

        Student newStudent = studentInputDTO.studentInputDto(persona.get(),setProfesorToStudent.get());

        return new StudentOutputDTO(studentRepository.save(newStudent));
        /*  TODO PREGUNTAR CUAL DE LAS 2 FORMAS DEJARLO
        Se podrían quitar las 2 lineas anteriores por :
            return new StudentOutputDto(studentRepository.save(studentInputDTO.studentInputDto(persona.get(),setProfesorToStudent.get())));
        Pero he preferido dejarlo de esa forma para una mejor comprension lectora
         */
    }

    @Override
    public List<StudentOutputDTO> findAllStudents() throws EntityNotFoundException {
        List<Student> studentList = studentRepository.findAll();

        if (studentList.isEmpty()) {
            throw new EntityNotFoundException("NO HAY NINGUN ESTUDIANTE CREADO AUN", 404);
        }

        return studentList.stream().map(StudentOutputDTO::new).collect(Collectors.toList());
        /*
        TODO PREGUNTAR POR QUE ES MEJOR LA LINEA DE ARRIBA QUE LA DE ABAJO:
        return studentList.stream().map(student -> new StudentOutputDTO(student)).collect(Collectors.toList());
         */
    }

    @Override
    public StudentOutputDTO findStudentById(String id_student) throws EntityNotFoundException {

        Student student = studentRepository.findById(id_student).orElse(null);

        if (student == null) {
            throw new EntityNotFoundException("EL ESTUDIANTE CON ESE IDSTUDENT NO EXISTE", 404);
        }

        return new StudentOutputDTO(student);
    }

    @Override
    public StudentOutputDTO findStudentByPersona(String id_persona) throws EntityNotFoundException {
        System.err.println("EL ID DE LA PERSONA EN LA IMPL DE STUDENT ES: " + id_persona);
        Persona persona = personaService.findPersonaById(id_persona).orElse(null);

        if (persona == null) {
            throw new EntityNotFoundException("DICHA PERSONA NO EXISTE", 404);
        }

        Student student = studentRepository.findStudentByPersona(persona).orElse(null);

        if (student == null) {
            throw new EntityNotFoundException("DICHA PERSONA NO ES UN ESTUDIANTE", 404);
        }

        return new StudentOutputDTO(student);
    }

    @Override
    public void deleteStudentById(String id_student) throws EntityNotFoundException {
        Student student = studentRepository.findById(id_student).orElse(null);

        if (student == null) {
            throw new EntityNotFoundException("EL ESTUDIANTE CON EL IDESTUDIANTE QUE INTENTAS ELIMINAR NO EXISTE", 404);
        }

        studentRepository.delete(student);
    }

    @Override
    public void deleteStudentByPersona(String id_persona) throws EntityNotFoundException {
        Persona persona = personaService.findPersonaById(id_persona).orElse(null);

        if (persona == null) {
            throw new EntityNotFoundException("DICHA PERSONA CON EL IDPERSONA NO EXISTE O NO ES ALUMNO", 404);
        }

        Student student = studentRepository.findStudentByPersona(persona).orElse(null);

        if (student == null) {
            throw new EntityNotFoundException("DICHA PERSONA NO ES SALUMNO", 404);
        }

        studentRepository.delete(student);
    }

    @Override
    public StudentOutputDTO updateStudentById(String id_student, StudentInputDTO studentInputDTO) throws EntityNotFoundException {

        Student student = studentRepository.findById(id_student).orElse(null);

        if (student == null) {
            throw new EntityNotFoundException("EL ESTUDIANTE POR IDSTUDENT NO EXISTE", 404);
        }

        student.setBranch(studentInputDTO.getBranch());
        student.setComments(studentInputDTO.getComments());
        student.setNumb_hours(studentInputDTO.getNumb_hours());

        if (studentInputDTO.getId_persona() != null) {
            Persona persona = personaService.findPersonaById(studentInputDTO.getId_persona()).orElse(null);

            if (persona == null) {
                throw new EntityNotFoundException("LA NUEVA PERSONA CON EL ID " + studentInputDTO.getId_persona() + " NO EXISTE",404);
            }
            if (studentRepository.findStudentByPersona(persona).isPresent()) {
                throw new UnprocessableEntityException("NO ES POSIBLE DEBIDO A QUE YA HAY UN ESATUDIANTE CON ESE IDPERSONA", 422);
            }
            if (profesorRepository.findProfesorByPersona(persona).isPresent()) {
                throw new UnprocessableEntityException("NO ES POSIBLE DEBIDO A QUE YA HAY UN PROFESOR CON ESE IDPERSONA", 422);
            }
            student.setPersona(persona);
        } else {
            throw new UnprocessableEntityException("EL IDPERSONA NO HA SIDO ESPECIFICADO", 422);
        }

        if (studentInputDTO.getId_profesor() != null) {
            Profesor profesor = profesorService.findProfesorById(studentInputDTO.getId_profesor()).orElse(null);

            if (profesor == null) {
                throw new EntityNotFoundException("EL NUEVO PROFESOR CON EL ID " + studentInputDTO.getId_profesor() + " NO EXISTE", 404);
            }
            if (findStudentByPersona(studentInputDTO.getId_persona()) != null) {
                throw new UnprocessableEntityException("NO ES POSIBLE DEBIDO A QUE YA HAY UN ESATUDIANTE CON ESE IDPERSONA", 422);
            }
            if (profesorRepository.findProfesorByPersona(profesor.getPersona()).isPresent()) {
                throw new UnprocessableEntityException("NO ES POSIBLE DEBIDO A QUE YA HAY UN PROFESOR CON ESE IDPERSONA", 422);
            }
            student.setProfesor(profesor);
        } else {
            throw new UnprocessableEntityException("EL IDPROFESOR NO HA SIDO ESPECIFICADO", 422);
        }

        return new StudentOutputDTO(studentRepository.save(student));
    }

    @Override
    @Transactional
    public String setAsignatura(String id_student, List<String> id_asignaturas) throws EntityNotFoundException {

        Student student = studentRepository.findById(id_student).orElse(null);

        if (student == null) {
            throw new EntityNotFoundException("NO EXISTE NINGUN STUDENT CON DICHO IDSTUDENT", 404);
        }

        for (String id_asignatura: id_asignaturas) {
            Asignatura asignatura = asignaturaRepository.findById(id_asignatura).orElse(null);

            if (asignatura == null) {
                throw new EntityNotFoundException("LA ASIGNATURA CON EL IDASIGNATURA: " + id_asignatura + " NO EXISTE", 404);
            }
            if (asignatura.getStudents().contains(student)) {
                throw new UnprocessableEntityException("ESTE ESTUDIANTE YA TIENE LA ASIGNATURA CON EL ID " + id_asignatura + " asignado", 422);
            }

            System.err.println("LAS ASIGNATURAS QUE HAY ANTES DE ASIGNARES SON: " + student.getAsignaturaList());
            asignatura.getStudents().add(student);
            System.err.println("LAS ASIGNATURAS QUE SE ASIGNAN AL FINAL SON: " + student.getAsignaturaList());
            asignaturaRepository.save(asignatura);
        }
        return "Las asignaturas " + id_asignaturas + " han sido asignadas al estudiante " + id_student;
    }
}
