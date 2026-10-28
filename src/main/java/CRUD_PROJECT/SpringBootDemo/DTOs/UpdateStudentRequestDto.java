package CRUD_PROJECT.SpringBootDemo.DTOs;

import jakarta.validation.constraints.*;

public class UpdateStudentRequestDto {

    @NotBlank(message = "Name cannot be null/Empty or blank")
    @Size(min = 2, max = 50, message = "Student name must be within 2 to 50 character long")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Student must be atleast 18 years old")
    private  int age;

    @NotBlank(message = "Student email cannot be blank")
    @Email(message = "Student email must be valid")
    private  String email;

    @NotNull(message = "RollNo is required")
    private int rollNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
}
