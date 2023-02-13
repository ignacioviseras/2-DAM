package com.example.persistence;

public class DAOException extends Exception {

    public DAOException(String message) {
        System.out.println(message);
    }

    public DAOException(Throwable cause) {
        System.out.println(cause.getMessage());
    }

    public DAOException(String message, Throwable cause) {
        System.out.println(message);
    }
    
}
