package it.unibz.ddd.webapp;

import it.unibz.ddd.model.Student;
import it.unibz.ddd.service.RepositoryFactory;
import it.unibz.ddd.service.StudentService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/student")
public class StudentEndpoint {
    @GET
    public Student get(@QueryParam("id") String id) {
        RepositoryFactory.configure(new FakeRepository());
        return new StudentService().getStudentWith(id);
    }
}
