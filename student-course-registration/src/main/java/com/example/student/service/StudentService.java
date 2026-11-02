package com.example.student.service;

import com.example.student.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();
    private long nextId = 1;

    public Student registerStudent(Student student) {
        student.setStudentId(nextId++);
        students.add(student);
        return student;
    }

    public boolean emailExists(String email) {
        for (Student student : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Student> findByCourse(String course) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getCourseName().equalsIgnoreCase(course)) {
                result.add(student);
            }
        }
        return result;
    }

    public Student updateStudent(Long id, Student newStudent) {
        Student student = findStudentById(id);
        if (student != null) {
            student.setStudentName(newStudent.getStudentName());
            student.setCourseName(newStudent.getCourseName());
            student.setEmail(newStudent.getEmail());
            student.setAge(newStudent.getAge());
        }
        return student;
    }

    public Student findStudentById(Long id) {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public boolean deleteRegistration(Long id) {
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }
}
