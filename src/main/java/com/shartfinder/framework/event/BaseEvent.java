package com.shartfinder.framework.event;

import java.util.UUID;

/**
 * Base class used to handle some standard methods and fields so that the
 * subclasses can just be specific to what they need.
 */
public abstract class BaseEvent<T extends EventType> implements Event<T> {

    private final T type;

    private final UUID aggregateId;

    public BaseEvent(UUID aggregateId, T type) {
        this.aggregateId = aggregateId;
        this.type = type;
    }

    @Override
    public T getType() {
        return type;
    }

    @Override
    public UUID getAggregateId() {
        return aggregateId;
    }

}
