package com.shartfinder.encounter.command.framework;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shartfinder.encounter.command.events.EncounterCreatedEvent;
import com.shartfinder.encounter.command.events.InitiativeOrderUpdatedEvent;
import com.shartfinder.framework.event.Event;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InitiativeOrderUpdatedEvent.class, name = "InitiativeOrderUpdated"),
        @JsonSubTypes.Type(value = EncounterCreatedEvent.class, name = "EncounterCreated") })
public interface EncounterEvent extends Event<EncounterEventType> {

}
