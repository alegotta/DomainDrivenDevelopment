package it.unibz.ddd.service;

import it.unibz.ddd.model.Student;

import java.util.Set;

public class StudentService {
    private final StudentRepository repo;

    public StudentService() {
        this.repo = RepositoryFactory.fromStudent();
    }

    public Student getStudentWith(String id) {
        return repo.loadStudentWith(id);
    }

    public Set<String> getCoursesFor(String id) {
        return getStudentWith(id).getCourses();
    }

    public String getCourseStatusFor(String studentId, String courseId) {
        return getStudentWith(studentId).getStatusForCourse(courseId);
    }
}
