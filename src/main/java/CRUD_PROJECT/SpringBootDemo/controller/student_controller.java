package CRUD_PROJECT.SpringBootDemo.controller;

import java.util.List;

import CRUD_PROJECT.SpringBootDemo.DTOs.controllerRequestDto.CreateStudentRequestDto;
import CRUD_PROJECT.SpringBootDemo.DTOs.controllerResponseDto.CreateStudentResponseDto;
import CRUD_PROJECT.SpringBootDemo.DTOs.controllerRequestDto.UpdateStudentRequestDto;
import CRUD_PROJECT.SpringBootDemo.DTOs.controllerResponseDto.UpdateStudentResponseDto;
import CRUD_PROJECT.SpringBootDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController        //I want when I use /api/student/   endpoint this method should be called
@RequestMapping("/api/students")
public class student_controller {

    private StudentService studentService;

    public student_controller(StudentService studentService) {
        this.studentService = studentService;
    }


 // create endpoint
    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(@Valid @RequestBody CreateStudentRequestDto student){
        //create student--> post --> /api/student/create  --> +(body)

        CreateStudentResponseDto createdStudent =  studentService.createStudent(student);

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }


    //read one record student--> GET --> /api/student/get/id(1/2...)

    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getOne(@PathVariable Long id){

        CreateStudentResponseDto getReq = studentService.getStudent(id);

        return ResponseEntity.
                status(200)
                .body(getReq);

    }



        //read all --> GET --> /api/student/getAll
        @GetMapping
        public ResponseEntity<List<CreateStudentResponseDto>> getAll(){

            List<CreateStudentResponseDto> getAllStudent = studentService.getALl();

            return ResponseEntity.ok(getAllStudent);
        }



        //update student --> PUT --> /api/student/update/{id}  with body
        @PutMapping("/{id}")

        public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id , @Valid @RequestBody UpdateStudentRequestDto reqStd){

            UpdateStudentResponseDto upOne = studentService.Update(id, reqStd);

            return ResponseEntity.ok(upOne);

        }





        //delete student --> DELETE --> /api/student/delete/{id}

        @DeleteMapping("/{id}")
        public ResponseEntity<String> isDeleted(@PathVariable Long id){

            studentService.delete(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

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

            studentService.softDelete(id);

            return ResponseEntity.noContent().build();

        }
}

