package com.shartfinder.core.api.chat;


public interface SendChatMessageCommand {

    void execute(String message, boolean systemMessage, String username);

}
