package edu.t1.exception;

public class TestNoStaticMethodException extends RuntimeException{
    public TestNoStaticMethodException(String message) {
        super(message);
    }
}
