package it.unibz.ddd.webapp;

import it.unibz.ddd.model.Student;
import it.unibz.ddd.service.RepositoryFactory;
import it.unibz.ddd.service.StudentService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/courses")
public class CoursesEndpoint {
    @GET
    public Set<CoursesEndpointResponse> get(@QueryParam("id") String id) {
        RepositoryFactory.configure(new FakeRepository());
        Student student = new StudentService().getStudentWith(id);

        return student.getCourses()
                .stream().map(courseId ->
                        new CoursesEndpointResponse(courseId, student.getStatusForCourse(courseId))
                )
                .collect(Collectors.toSet());
    }
}

