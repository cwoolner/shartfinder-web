package com.shartfinder.framework.event;

public interface EventSubscriber<T extends EventType> {

    void receive(Event<T> event);

}
