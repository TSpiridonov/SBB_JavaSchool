package ru.javaschool.sbb.exception;

public class NoAvaliableTicketException extends RuntimeException{

    public NoAvaliableTicketException(){
    }

    public NoAvaliableTicketException(String message){
        super(message);
    }
    public NoAvaliableTicketException(String message, Throwable cause){
        super(message, cause);
    }
}
