package it.unibz.ddd.webapp;

public class CoursesEndpointResponse {
    private final String courseId;
    private final String status;

    public CoursesEndpointResponse(String courseId, String status) {
        this.courseId = courseId;
        this.status = status;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getStatus() {
        return status;
    }
}
