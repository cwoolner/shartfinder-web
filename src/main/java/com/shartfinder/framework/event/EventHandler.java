package com.shartfinder.framework.event;

public interface EventHandler<T extends Event<? extends EventType>> {

    void handle(T event);

}
