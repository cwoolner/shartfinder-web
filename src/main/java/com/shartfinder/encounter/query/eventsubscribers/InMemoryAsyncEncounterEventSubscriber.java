package com.shartfinder.encounter.query.eventsubscribers;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.shartfinder.encounter.command.events.EncounterCreatedEvent;
import com.shartfinder.encounter.command.events.InitiativeOrderUpdatedEvent;
import com.shartfinder.encounter.command.framework.EncounterEventType;
import com.shartfinder.framework.event.Event;
import com.shartfinder.framework.event.EventHandler;
import com.shartfinder.framework.event.EventSubscriber;

@Component
public class InMemoryAsyncEncounterEventSubscriber implements
        EventSubscriber<EncounterEventType> {

    private final EventHandler<EncounterCreatedEvent> tableCreatedEventHandler;

    private final EventHandler<InitiativeOrderUpdatedEvent> playerCheckedEventHandler;

    @Inject
    public InMemoryAsyncEncounterEventSubscriber(
            EventHandler<EncounterCreatedEvent> tableCreatedEventHandler,
            EventHandler<InitiativeOrderUpdatedEvent> playerCheckedEventHandler) {
        this.tableCreatedEventHandler = tableCreatedEventHandler;
        this.playerCheckedEventHandler = playerCheckedEventHandler;
    }

    @Async
    @Override
    public void receive(Event<EncounterEventType> event) {
        switch (event.getType()) {
        case EncounterCreated:
            tableCreatedEventHandler.handle((EncounterCreatedEvent) event);
            break;
        case InitiativeOrderUpdated:
            playerCheckedEventHandler.handle((InitiativeOrderUpdatedEvent) event);
            break;
        default:
            throw new IllegalArgumentException("Event Type cannot be handled: "
                    + event.getType());
        }
    }

}
