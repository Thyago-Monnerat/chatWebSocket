package com.ws.websocket.exceptionHandlers;

import com.ws.websocket.exceptions.NameDupeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @ExceptionHandler(NameDupeException.class)
    public void handleNameDupeException(NameDupeException e) {
        messagingTemplate.convertAndSend("/topics/errors", e.getMessage());
    }
}
