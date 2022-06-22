package it.unibz.ddd;

import it.unibz.ddd.model.PhoneContact;
import it.unibz.ddd.model.PhoneType;
import it.unibz.ddd.model.Student;
import it.unibz.ddd.model.events.Conclusion;
import it.unibz.ddd.model.events.Enrollment;
import it.unibz.ddd.model.events.Withdraw;
import it.unibz.ddd.service.RepositoryFactory;
import it.unibz.ddd.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class TestDDDModel {
    public static final String fakeIdForRepository = "1234";

    @Test
    public void contactNotDuplicated() {
        PhoneContact p1 = new PhoneContact("IT", "+39", "0474122334", PhoneType.Work);
        PhoneContact p2 = new PhoneContact("IT", "+39", "0474122334", PhoneType.Personal);

        Student student = new StudentService().getStudentWith(fakeIdForRepository);
        student.addContact(p1);
        student.addContact(p2);

        Assertions.assertEquals(student.getContacts().size(), 1);
    }

    @Test
    public void lazyLoadEvents() {
        RepositoryFactory.configure(new FakeRepository());
        Student student = new StudentService().getStudentWith(fakeIdForRepository);

        Assertions.assertEquals(student.getEvents().size(), 5);
    }

    @Test
    public void getCourseNames() {
        RepositoryFactory.configure(new FakeRepository());
        StudentService svc = new StudentService();

        Set<String> courses = svc.getCoursesFor(fakeIdForRepository);

        Assertions.assertEquals(
                courses.size(),
                3
        );
        Assertions.assertTrue(courses.contains("SwSysAr"));
        Assertions.assertTrue(courses.contains("ToTeSwTe"));
        Assertions.assertTrue(courses.contains("Program"));
    }

    @Test
    public void getCourseStatus() {
        RepositoryFactory.configure(new FakeRepository());
        StudentService svc = new StudentService();

        Assertions.assertEquals(
                svc.getCourseStatusFor(fakeIdForRepository,"SwSysAr"),
                "WITHDRAW"
        );
        Assertions.assertEquals(
                svc.getCourseStatusFor(fakeIdForRepository,"ToTeSwTe"),
                "PASSED"
        );
        Assertions.assertEquals(
                svc.getCourseStatusFor(fakeIdForRepository,"Program"),
                "ENROLLED"
        );
    }

    @Test
    public void addEvent() {
        RepositoryFactory.configure(new FakeRepository());
        Student student = new Student("1231", "Bianca Bianchi");

        student.addEvent(new Enrollment("SwSysAr"));
        student.addEvent(new Withdraw("SwSysAr"));
        Assertions.assertThrows(RuntimeException.class, () -> {
            student.addEvent(new Withdraw("ToTeSoTe"));
        });

        student.addEvent(new Enrollment("SwSysAr"));
        student.addEvent(new Conclusion("SwSysAr", false));
        student.addEvent(new Enrollment("SwSysAr"));
        student.addEvent(new Conclusion("SwSysAr", true));
        Assertions.assertThrows(RuntimeException.class, () -> {
            student.addEvent(new Enrollment("SwSysAr"));
        });
    }
}
