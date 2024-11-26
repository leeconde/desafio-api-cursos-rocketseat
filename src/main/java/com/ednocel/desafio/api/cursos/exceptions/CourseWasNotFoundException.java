package com.ednocel.desafio.api.cursos.exceptions;

public class CourseWasNotFoundException extends RuntimeException {

    public CourseWasNotFoundException() {
        super("The course was not found");
    }
}
