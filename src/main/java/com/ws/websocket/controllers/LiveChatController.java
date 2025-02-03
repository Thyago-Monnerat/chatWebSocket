package com.ws.websocket.controllers;

import com.ws.websocket.dtos.MsgDTO;
import com.ws.websocket.services.LiveChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LiveChatController {

    @Autowired
    private LiveChatService liveChatService;

    @MessageMapping("/msg")
    @SendTo("/topics/chat")
    public MsgDTO handleMessages(MsgDTO msgDTO) {
        return msgDTO;
    }
}