package com.shartfinder.encounter.query.eventsubscribers;

import javax.inject.Inject;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.shartfinder.encounter.command.events.EncounterCreatedEvent;
import com.shartfinder.encounter.command.events.InitiativeOrderCreatedEvent;
import com.shartfinder.encounter.command.framework.EncounterEvent;
import com.shartfinder.encounter.command.framework.EncounterEventType;
import com.shartfinder.framework.event.Event;
import com.shartfinder.framework.event.EventHandler;
import com.shartfinder.framework.event.EventSubscriber;

@Profile("prod")
@Component
public class RedisEncounterEventSubscriber implements EventSubscriber<EncounterEventType> {

    private final RedisTemplate<String, EncounterEvent> redisTemplateEncounterEvent;

    private final EventHandler<EncounterCreatedEvent> tableCreatedEventHandler;

    private final EventHandler<InitiativeOrderCreatedEvent> playerCheckedEventHandler;

    @Inject
    public RedisEncounterEventSubscriber(
            RedisTemplate<String, EncounterEvent> redisTemplateEncounterEvent,
            EventHandler<EncounterCreatedEvent> tableCreatedEventHandler,
            EventHandler<InitiativeOrderCreatedEvent> playerCheckedEventHandler) {
        this.redisTemplateEncounterEvent = redisTemplateEncounterEvent;
        this.tableCreatedEventHandler = tableCreatedEventHandler;
        this.playerCheckedEventHandler = playerCheckedEventHandler;
    }

    @Async
    @Override
    public void receive(Event<EncounterEventType> event) {
        System.out.println(event);
    }

}
