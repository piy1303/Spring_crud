package CRUD_PROJECT.SpringBootDemo.repository;

import CRUD_PROJECT.SpringBootDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface Student_repository extends JpaRepository<Student, Long> {



}
