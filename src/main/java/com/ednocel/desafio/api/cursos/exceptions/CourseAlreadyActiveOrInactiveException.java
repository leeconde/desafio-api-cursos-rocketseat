package com.ednocel.desafio.api.cursos.exceptions;

public class CourseAlreadyActiveOrInactiveException extends RuntimeException {

    public CourseAlreadyActiveOrInactiveException(String message) {
        super("The course already has the status: " + message);
    }
}
