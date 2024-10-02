package com.example.bookingservice.exception;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(String message) {
        super(message); // Передаем сообщение в родительский класс RuntimeException
    }

    public AppointmentNotFoundException(String message, Throwable cause) {
        super(message, cause); // Позволяет передавать сообщение и причину исключения
    }
}
