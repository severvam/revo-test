package com.revo.core;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    public Response toResponse(ConstraintViolationException cex) {
        final String message = cex.getConstraintViolations()
                .stream()
                .map(constraint -> constraint.getPropertyPath().toString() + " " + constraint.getMessage())
                .collect(Collectors.joining(", "));
        final OperationResult operationResult = OperationResult.create(false, message);
        return Response.status(400).entity(operationResult).build();
    }
}