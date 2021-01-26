package ru.javaschool.sbb.exception;

public class TripCompletedException extends RuntimeException {
    public TripCompletedException() {
    }

    public TripCompletedException(String message) {
        super(message);
    }

    public TripCompletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
