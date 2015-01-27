package com.shartfinder.framework.command;


public interface CommandPublisher<T extends CommandType> {

    void publish(Command<T> command);

}
