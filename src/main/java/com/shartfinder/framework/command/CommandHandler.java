package com.shartfinder.framework.command;


public interface CommandHandler<T extends Command<? extends CommandType>> {

    void handle(T command);

}
