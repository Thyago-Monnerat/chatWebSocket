package com.ws.websocket.events;

import com.ws.websocket.dtos.MsgDTO;
import com.ws.websocket.services.LiveChatService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EventsListener {

    private final SimpMessagingTemplate messagingTemplate;

    private final LiveChatService liveChatService;

    protected ConcurrentHashMap<String, String> usersSession = new ConcurrentHashMap<>();

    public EventsListener(SimpMessagingTemplate messagingTemplate, LiveChatService liveChatService) {
        this.messagingTemplate = messagingTemplate;
        this.liveChatService = liveChatService;
    }

    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = headerAccessor.getFirstNativeHeader("login");

        liveChatService.messageValidator(username);

        if(username != null && headerAccessor.getSessionId() != null){
            this.usersSession.put(headerAccessor.getSessionId(), username);
        }

        MsgDTO chatOutput = new MsgDTO(username, "entrou no chat");
        messagingTemplate.convertAndSend("/topics/chat", chatOutput);
    }

    @EventListener
    public void handleSessionDisconnected(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = usersSession.get(headerAccessor.getSessionId());

        liveChatService.disconnect(username);

        if(headerAccessor.getSessionId() != null){
            usersSession.remove(headerAccessor.getSessionId());
        }

        MsgDTO chatOutput = new MsgDTO(username, "saiu do chat");
        messagingTemplate.convertAndSend("/topics/chat", chatOutput);

    }

}