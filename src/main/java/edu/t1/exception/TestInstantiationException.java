package edu.t1.exception;

public class TestInstantiationException extends RuntimeException{
    public TestInstantiationException(String message, Exception e) {
        super(message, e);
    }
}
