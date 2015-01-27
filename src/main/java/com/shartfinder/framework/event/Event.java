package com.shartfinder.framework.event;

import java.util.UUID;

public interface Event<T extends EventType> {

    UUID getAggregateId();

    T getType();

}
