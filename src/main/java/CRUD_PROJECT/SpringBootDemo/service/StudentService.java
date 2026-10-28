package CRUD_PROJECT.SpringBootDemo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import CRUD_PROJECT.SpringBootDemo.DTOs.CreateStudentRequestDto;
import CRUD_PROJECT.SpringBootDemo.DTOs.CreateStudentResponseDto;
import CRUD_PROJECT.SpringBootDemo.DTOs.UpdateStudentRequestDto;
import CRUD_PROJECT.SpringBootDemo.DTOs.UpdateStudentResponseDto;
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

        Student std1 = studentRepository.save(std);

        return EntityToDto(std1);


    }


    public Optional<CreateStudentResponseDto> getStudent(Long id) {

        Optional<Student> getReq = studentRepository.findByIdAndValidDeleteIsFalse(id);

        return getReq.map(this::EntityToDto);
    }


    public List<CreateStudentResponseDto> getALl() {

        List<Student> getAllStudent = studentRepository.findByValidDeleteIsFalse();

        return getAllStudent.stream().map(this::EntityToDto).toList();
    }

    public UpdateStudentResponseDto Update(Long id , UpdateStudentRequestDto reqStd) {

        Optional<Student> getStudent = studentRepository.findByIdAndValidDeleteIsFalse(id);

        if (getStudent.isEmpty()) return null;

        Student currStudent = getStudent.get();


        currStudent.setName(reqStd.getName());
        currStudent.setRollNo(reqStd.getRollNo());
        currStudent.setEmail(reqStd.getEmail());
        currStudent.setAge(reqStd.getAge());

        currStudent.setValidDelete(false);

        Student s = studentRepository.save(currStudent);

        return UpdateEntityTODto(s);

    }


    public boolean delete(Long id) {

        boolean getStudent = studentRepository.existsById(id);

        if (!getStudent) return false;

        studentRepository.deleteById(id);

        return true;

    }

    public boolean softDelete(long id) {

        Optional<Student> exsistingstudent = studentRepository.findByIdAndValidDeleteIsFalse(id);

        if (exsistingstudent.isEmpty()){
            return false;
        }

        Student markedStudent = exsistingstudent.get();

        markedStudent.setValidDelete(true);

        studentRepository.save(markedStudent);

        return true;

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

}
