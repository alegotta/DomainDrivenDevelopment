package it.unibz.ddd.service.database;

import it.unibz.ddd.model.Student;
import it.unibz.ddd.model.events.CourseEvent;
import it.unibz.ddd.service.StudentRepository;
import it.unibz.ddd.service.database.exceptions.NotFoundException;

import java.util.List;
import java.util.Map;

public class DatabaseRepository implements StudentRepository {

    public DatabaseRepository() {
        Map<String, String> properties = new PropertiesReader().getContent();
        Db.connect(properties.getOrDefault("dbms", "postgresql"),
                properties.getOrDefault("host", "localhost"),
                properties.getOrDefault("port", "5432"),
                properties.get("db"),
                properties.get("username"),
                properties.get("password"));
    }

    @Override
    public List<CourseEvent> getEventsByStudent(String studentId) {
        List<StudentEventMapper> mapper = new Query<>("SELECT CourseEvent.id,type,passed FROM CourseEvent, Student WHERE Student.id=CourseEvent.studentid AND Student.id=?", StudentEventMapper.class, new Parameter<>(studentId)).run();
        return mapper.stream().map(StudentEventMapper::toCourseEvent).toList();
    }

    @Override
    public Student loadStudentWith(String id) {
        return new Query<>("SELECT id,name FROM Student WHERE id=?", Student.class, new Parameter<>(id)).run()
                .stream().findFirst().orElseThrow(() -> new NotFoundException("3D", "Student query", id));
    }


}
