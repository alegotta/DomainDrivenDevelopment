package it.unibz.ddd;

import it.unibz.ddd.model.Student;
import it.unibz.ddd.model.events.Conclusion;
import it.unibz.ddd.model.events.CourseEvent;
import it.unibz.ddd.model.events.Enrollment;
import it.unibz.ddd.model.events.Withdraw;
import it.unibz.ddd.service.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeRepository implements StudentRepository {
    @Override
    public List<CourseEvent> getEventsByStudent(String studentId) {
        if (studentId.equals(TestDDDModel.fakeIdForRepository))
            return List.of(
                    new Enrollment("SwSysAr"),
                    new Withdraw("SwSysAr"),
                    new Enrollment("ToTeSwTe"),
                    new Conclusion("ToTeSwTe", true),
                    new Enrollment("Program")
            );
        else return new ArrayList<>();


    }

    @Override
    public Student loadStudentWith(String id) {
        if (TestDDDModel.fakeIdForRepository.equals(id))  //Always use equals on the fixed value (id could be null)
            return new Student(TestDDDModel.fakeIdForRepository, "Mario Rossi");
        else
            return null;
    }
}
