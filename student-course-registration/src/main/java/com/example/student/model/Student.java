package com.example.student.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Student {

    private Long studentId;

    @NotBlank(message = "Student name cannot be empty")
    @Size(min = 2, max = 50, message = "Student name must be 2 to 50 characters")
    @Pattern(regexp = "^[A-Za-z ]*$", message = "Student name can contain only letters and spaces")
    private String studentName;

    @NotBlank(message = "Course name is required")
    private String courseName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter valid email")
    private String email;

    @Min(value = 18, message = "Age must be above 18")
    @Max(value = 60, message = "Age cannot be more than 60")
    private int age;

    public Student() {}

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
