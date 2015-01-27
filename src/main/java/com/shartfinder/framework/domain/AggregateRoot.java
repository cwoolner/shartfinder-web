package com.shartfinder.framework.domain;

import java.util.ArrayList;
import java.util.List;

import com.shartfinder.framework.event.Event;
import com.shartfinder.framework.event.EventType;

public abstract class AggregateRoot<T extends Event<? extends EventType>> {

    private final List<T> newEvents = new ArrayList<>();

    private final List<T> appliedEvents = new ArrayList<>();

    protected void addNewEvent(T event) {
        newEvents.add(event);
    }

    protected void addAppliedEvent(T event) {
        appliedEvents.add(event);
    }

    public List<T> fetchNewEvents() {
        return new ArrayList<>(newEvents);
    }

    public abstract void applyAllHistoricalEvents(List<T> events);

    public abstract void applyAllNewEvents(List<T> events);

}
