package it.unibz.ddd.service;

import it.unibz.ddd.model.Student;
import it.unibz.ddd.model.events.CourseEvent;

import java.util.List;

public interface StudentRepository {
    public List<CourseEvent> getEventsByStudent(String studentId);

    public Student loadStudentWith(String id);
}
