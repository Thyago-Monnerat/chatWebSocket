package com.ws.websocket.services;

import com.ws.websocket.exceptions.NameDupeException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LiveChatService {

    private final Set<String> userList = new HashSet<>();

    public void messageValidator(String name){

        if(userList.contains(name)){
            throw new NameDupeException("Nome duplicado");
        }else{
            userList.add(name);
        }
    }

    public void disconnect(String name){
        this.userList.remove(name);
    }
}
