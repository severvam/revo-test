package com.revo.core.web;

import lombok.Value;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        final String message = e.getMessage() != null ? e.getMessage() : "Something goes wrong, but don't worry, we're working on that";
        return Response.serverError().entity(new ErrorResponse(message)).type(MediaType.APPLICATION_JSON).build();
    }

    @Value
    private static class ErrorResponse {

        private final String message;

    }


}
