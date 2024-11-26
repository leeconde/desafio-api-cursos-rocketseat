package com.ednocel.desafio.api.cursos.exceptions;

public class CategoriaInvalidaException extends RuntimeException{

    public CategoriaInvalidaException() {
        super("Categoria informada não existe.");
    }
}
