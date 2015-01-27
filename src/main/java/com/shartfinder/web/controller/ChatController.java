package com.shartfinder.web.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.shartfinder.core.api.chat.SendChatMessageCommand;

@Controller
public class ChatController {

    private final SendChatMessageCommand sendChatMessageCommand;

    @Inject
    public ChatController(SendChatMessageCommand sendChatMessageCommand) {
        this.sendChatMessageCommand = sendChatMessageCommand;
    }

    @MessageMapping(value = "/app/sendchatmessage")
    public void sendChatMessage(String message, Principal principal) {
        sendChatMessageCommand.execute(message, false, principal.getName());
    }

}
