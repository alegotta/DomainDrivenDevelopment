package it.unibz.ddd;

import it.unibz.ddd.service.RepositoryFactory;
import it.unibz.ddd.service.database.DatabaseRepository;
import it.unibz.ddd.service.database.Db;
import it.unibz.ddd.service.database.exceptions.DbException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class TestDatabase {
    private static DatabaseRepository repo;
    @BeforeAll
    public static void init() {
        repo = new DatabaseRepository();
        RepositoryFactory.configure(repo);
        try {
            Db instance = Db.getInstance();
            instance.insert("INSERT INTO Student(id,name) VALUES ('2345', 'Johnny');", Collections.emptyList());
            instance.insert("INSERT INTO CourseEvent(id,studentId,type) VALUES ('SwSysAr','2345', 'ENROLLMENT');", Collections.emptyList());
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getStudent() {
        Assertions.assertEquals(repo.loadStudentWith("2345").getId(), "2345");
    }

    @Test
    public void getEvents() {
        Assertions.assertEquals(repo.loadStudentWith("2345").getStatusForCourse("SwSysAr"), "ENROLLED");
    }

    @AfterAll
    public static void tearDown() {
        try {
            Db instance = Db.getInstance();
            instance.insert("DELETE FROM CourseEvent;", Collections.emptyList());
            instance.insert("DELETE FROM Student;", Collections.emptyList());
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
