package it.unibz.ddd.webapp;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

//@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ExceptionHandler implements ExceptionMapper<Exception> {
    static class ErrorMessage{
        String message;

        public ErrorMessage(String message) {
            this.message = message;
        }
    }

    @Override
    public Response toResponse(Exception e) {
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        if (e instanceof NullPointerException || e instanceof IllegalArgumentException)
            status = Response.Status.BAD_REQUEST;

        return Response.status(status).entity(new ErrorMessage(e.getLocalizedMessage())).build();
    }
}
