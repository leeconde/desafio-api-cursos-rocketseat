package com.ednocel.desafio.api.cursos.exceptions;

public class InvalidParametersException extends RuntimeException {
    public InvalidParametersException() {
        super("Invalid parameters sent in the request.");
    }
}
