package vg.utils;

public class NoSuchControllerException extends RuntimeException {

    public NoSuchControllerException() {
        super("View's controller is not set!");
    }
}
