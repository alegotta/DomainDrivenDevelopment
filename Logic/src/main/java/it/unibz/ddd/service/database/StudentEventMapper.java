package it.unibz.ddd.service.database;

import it.unibz.ddd.model.events.Conclusion;
import it.unibz.ddd.model.events.CourseEvent;
import it.unibz.ddd.model.events.Enrollment;
import it.unibz.ddd.model.events.Withdraw;

public class StudentEventMapper {
    private final String id;
    private final String type;
    private final int passed;

    public StudentEventMapper(String id, String type, int passed) {
        this.id = id;
        this.type = type;
        this.passed = passed;
    }

    public CourseEvent toCourseEvent() {
        if ("WITHDRAW".equalsIgnoreCase(type)) {
            return new Withdraw(id);
        } else if ("ENROLLMENT".equalsIgnoreCase(type)) {
            return new Enrollment(id);
        } else if ("CONCLUSION".equalsIgnoreCase(type)) {
            return new Conclusion(id, passed==1);
        } else
            throw new IllegalArgumentException();
    }
}
