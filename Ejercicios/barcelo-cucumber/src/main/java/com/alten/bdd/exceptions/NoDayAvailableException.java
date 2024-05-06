package com.alten.bdd.exceptions;

public class NoDayAvailableException extends Exception {

    public String msj;

    public NoDayAvailableException(String msj) {
        super(msj);
    }
}
