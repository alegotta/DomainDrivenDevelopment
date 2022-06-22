package it.unibz.ddd.webapp;

import it.unibz.ddd.model.PhoneContact;
import it.unibz.ddd.model.PhoneType;
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
        if (studentId.equals("1234"))
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
        if ("1234".equals(id)) { //Always use equals on the fixed value (id could be null)
            Student st = new Student("1234", "Mario Rossi");
            st.addContact(new PhoneContact("IT", "39", "12341234", PhoneType.Personal));
            st.addContact(new PhoneContact("IT", "39", "45674567", PhoneType.Work));
            return st;
        } else {
            return null;
        }
    }
}
