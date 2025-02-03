package com.ws.websocket.exceptions;

public class NameDupeException extends RuntimeException {
    public NameDupeException(String message) {
        super(message);
    }
}
