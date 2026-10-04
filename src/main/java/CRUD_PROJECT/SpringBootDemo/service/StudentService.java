package CRUD_PROJECT.SpringBootDemo.service;

import CRUD_PROJECT.SpringBootDemo.entity.Student;
import CRUD_PROJECT.SpringBootDemo.repository.Student_repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private Student_repository studentRepository;

    public StudentService(Student_repository studentRepository) {
        this.studentRepository = studentRepository;
    }




    public Student createStudent(Student studentReq) {
        //business logic --> store in db

        studentReq.setValidDelete(false);

        Student studentResponse = studentRepository.save(studentReq);
        return studentResponse;

    }


    public Optional<Student> getStudent(Long id) {

        Optional<Student> getReq = studentRepository.findByIdAndvaildDeleteIsFalse(id);

        return getReq;
    }


    public List<Student> getALl() {

        List<Student> getAllStudent = studentRepository.findByaildDeleteIsFalse();

        return getAllStudent;
    }

    public Student Update(Long id , Student reqStd) {

        Optional<Student> getStudent = studentRepository.findByIdAndvaildDeleteIsFalse(id);

        if (getStudent.isEmpty()) return null;

        Student currStudent = getStudent.get();

        currStudent.setName(reqStd.getName());
        currStudent.setSubject(reqStd.getSubject());
        currStudent.setRollNo(reqStd.getRollNo());
        currStudent.setEmail(reqStd.getEmail());
        currStudent.setAge(reqStd.getAge());

        currStudent.setValidDelete(false);

        return studentRepository.save(currStudent);

    }


    public boolean delete(Long id) {

        boolean getStudent = studentRepository.existsById(id);

        if (!getStudent) return false;

        studentRepository.deleteById(id);

        return true;

    }

    public boolean softDelete(long id) {

        Optional<Student> exsistingstudent = studentRepository.findByIdAndvaildDeleteIsFalse(id);

        if (exsistingstudent.isEmpty()){
            return false;
        }

        Student markedStudent = exsistingstudent.get();

        markedStudent.setValidDelete(true);

        studentRepository.save(markedStudent);

        return true;

    }

}
