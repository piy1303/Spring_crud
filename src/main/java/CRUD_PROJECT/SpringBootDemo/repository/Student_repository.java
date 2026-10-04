package CRUD_PROJECT.SpringBootDemo.repository;

import CRUD_PROJECT.SpringBootDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface Student_repository extends JpaRepository<Student, Long> {


        Optional<Student> findByIdAndvaildDeleteIsFalse(long id);

    List<Student>  findByaildDeleteIsFalse();
}
