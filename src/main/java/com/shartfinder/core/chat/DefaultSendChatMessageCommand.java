package com.shartfinder.core.chat;

import javax.inject.Inject;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import com.shartfinder.core.api.chat.SendChatMessageCommand;
import com.shartfinder.util.MessagingConstants;

@Component
public class DefaultSendChatMessageCommand implements SendChatMessageCommand {

    private final SimpMessageSendingOperations messagingTemplate;

    @Inject
    public DefaultSendChatMessageCommand(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void execute(String message, boolean systemMessage, String username) {
        if (systemMessage) {
            messagingTemplate.convertAndSend(MessagingConstants.CHAT_GLOBAL_SYSTEM,
                    "System: " + message);
        } else {
            messagingTemplate.convertAndSend(MessagingConstants.CHAT_GLOBAL_USER,
                    username + ": " + message);
        }
    }

}
