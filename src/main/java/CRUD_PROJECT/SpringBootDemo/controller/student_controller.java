package CRUD_PROJECT.SpringBootDemo.controller;

import CRUD_PROJECT.SpringBootDemo.entity.Student;
import CRUD_PROJECT.SpringBootDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController        //I want when I use /api/student/   endpoint this method should be called
@RequestMapping("/api/students")
public class student_controller {

    private StudentService studentService;

    public student_controller(StudentService studentService) {
        this.studentService = studentService;
    }


 // create endpoint
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        //create student--> post --> /api/student/create  --> +(body)

        Student createdStudent =  studentService.createStudent(student);

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }





    //read one record student--> GET --> /api/student/get/id(1/2...)

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Student>> getOne(@PathVariable Long id){

        Optional<Student> getReq = studentService.getStudent(id);

        if(getReq.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(getReq);

    }



        //read all --> GET --> /api/student/getAll
        @GetMapping("/getAll")
        public ResponseEntity<List<Student>> getAll(){

            List<Student> getAllStudent = studentService.getALl();


            if (getAllStudent.isEmpty()) return ResponseEntity.notFound().build();

            return ResponseEntity.ok(getAllStudent);
        }



        //update student --> PUT --> /api/student/update/{id}  with body
        @PutMapping("/update/{id}")

        public ResponseEntity<Optional<Student>> updateStudent(@PathVariable Long id , @RequestBody Student reqStd){

            Optional<Student> upOne = Optional.ofNullable(studentService.Update(id, reqStd));


            if (upOne.isEmpty()) return ResponseEntity.notFound().build();

            return ResponseEntity.ok(upOne);

        }





        //delete student --> DELETE --> /api/student/delete/{id}

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> isDeleted(@PathVariable Long id){

            boolean valid = studentService.delete(id);

            if (!valid) return ResponseEntity.notFound().build();

            return ResponseEntity.ok("deleted");

        }


        //  soft-delete student--> PATCH --> /api/student/delete-soft/{id} -->with path variable
        /*
        @PatchMapping("/delete-soft/{id}")
        public ResponseEntity<String> softlyDeleted(@PathVariable long id){

            boolean valid = studentService.softDelete(id);

            if (!valid){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok("Marked as softly deleted");

        }
        */


        //  soft-delete student--> PATCH --> /api/student/delete-soft/{id} -->with request parameter
        @PatchMapping("/delete-soft")
        public ResponseEntity<String> softlyDeleted(@RequestParam long id){

            boolean valid = studentService.softDelete(id);

            if (!valid){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok("Marked as softly deleted");

        }
}
