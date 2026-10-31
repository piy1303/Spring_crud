package CRUD_PROJECT.SpringBootDemo.service;

import java.time.LocalDateTime;
import java.util.List;

import CRUD_PROJECT.SpringBootDemo.exception.DuplicateResouceFoundException;
import CRUD_PROJECT.SpringBootDemo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import CRUD_PROJECT.SpringBootDemo.DTOs.controllerRequestDto.CreateStudentRequestDto;
import CRUD_PROJECT.SpringBootDemo.DTOs.controllerResponseDto.CreateStudentResponseDto;
import CRUD_PROJECT.SpringBootDemo.DTOs.controllerRequestDto.UpdateStudentRequestDto;
import CRUD_PROJECT.SpringBootDemo.DTOs.controllerResponseDto.UpdateStudentResponseDto;
import CRUD_PROJECT.SpringBootDemo.entity.Student;
import CRUD_PROJECT.SpringBootDemo.repository.Student_repository;

@Service
public class StudentService {

    private Student_repository studentRepository;

    public StudentService(Student_repository studentRepository) {
        this.studentRepository = studentRepository;
    }




    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentReq) {
        //business logic --> store in db

        Student std = DtoToEntity(studentReq);

        if (ExsitsByEmail(std)){
            throw new DuplicateResouceFoundException("Student with email: " + std.getEmail() + " already exists!");
        }

        Student std1 = studentRepository.save(std);

        return EntityToDto(std1);


    }


    public CreateStudentResponseDto getStudent(Long id) {

        Student getReq = studentRepository
                .findByIdAndValidDeleteIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " is not found!"));

        return EntityToDto(getReq);
    }


    public List<CreateStudentResponseDto> getALl() {

        List<Student> getAllStudent = studentRepository.findByValidDeleteIsFalse();

        return getAllStudent.stream().map(this::EntityToDto).toList();
    }

    public UpdateStudentResponseDto Update(Long id , UpdateStudentRequestDto reqStd) {

        Student currStudent = studentRepository
                .findByIdAndValidDeleteIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " is not found!"));


        currStudent.setName(reqStd.getName());
        currStudent.setRollNo(reqStd.getRollNo());
        currStudent.setEmail(reqStd.getEmail());
        currStudent.setAge(reqStd.getAge());

        currStudent.setValidDelete(false);
        currStudent.setUpdatedAt(String.valueOf(LocalDateTime.now()));

        Student s = studentRepository.save(currStudent);

        return UpdateEntityTODto(s);

    }


    public void delete(Long id) {

        Student stdudentToDelete = studentRepository
                .findByIdAndValidDeleteIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " is not found!"));

       studentRepository.delete(stdudentToDelete);

    }

    public void softDelete(long id) {

        Student markedStudent = studentRepository
        .findByIdAndValidDeleteIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " is not found!"));


        markedStudent.setValidDelete(true);

        studentRepository.save(markedStudent);

    }

    private Student DtoToEntity(CreateStudentRequestDto createStudentRequestDto){

        Student s1 = new Student();

        s1.setName(createStudentRequestDto.getName());
        s1.setAge(createStudentRequestDto.getAge());
        s1.setEmail(createStudentRequestDto.getEmail());
        s1.setRollNo(createStudentRequestDto.getRollNo());
        s1.setSubject(createStudentRequestDto.getSubject());

        s1.setValidDelete(false);
        s1.setCreatedAt(String.valueOf(LocalDateTime.now()));

        return s1;

    }


    private CreateStudentResponseDto EntityToDto(Student student){

        CreateStudentResponseDto csrd = new CreateStudentResponseDto();

        csrd.setId(student.getId());
        csrd.setName(student.getName());
        csrd.setAge(student.getAge());
        csrd.setEmail(student.getEmail());
        csrd.setRollNo(student.getRollNo());
        csrd.setSubject(student.getSubject());
        csrd.setCreatedAt(student.getCreatedAt());
        csrd.setMessage("Student record created sucessfully");

        return csrd;
    }

    private UpdateStudentResponseDto UpdateEntityTODto(Student student){

        UpdateStudentResponseDto usrd = new UpdateStudentResponseDto();

        usrd.setId(student.getId());
        usrd.setName(student.getName());
        usrd.setAge(student.getAge());
        usrd.setEmail(student.getEmail());
        usrd.setRollNo(student.getRollNo());
        usrd.setSubject(student.getSubject());
        usrd.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        usrd.setMessage("Student record updated sucessfully");

        return  usrd;

    }

    private boolean ExsitsByEmail(Student student){

        return studentRepository.existsByEmail(student.getEmail());
    }

}
