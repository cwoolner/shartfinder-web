package com.shartfinder.encounter.command.events;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shartfinder.encounter.command.framework.EncounterEvent;
import com.shartfinder.encounter.command.framework.EncounterEventType;
import com.shartfinder.framework.event.BaseEvent;

public class InitiativeOrderCreatedEvent extends BaseEvent<EncounterEventType> implements
        EncounterEvent {

    private static final EncounterEventType TYPE = EncounterEventType.InitiativeOrderCreated;

    private final List<UUID> playerIds;

    @JsonCreator
    public InitiativeOrderCreatedEvent(
            @JsonProperty(value = "aggregateId") UUID aggregateId,
            @JsonProperty(value = "playerIds") List<UUID> playerIds) {
        super(aggregateId, TYPE);
        this.playerIds = playerIds;
    }

    public List<UUID> getPlayerIds() {
        return playerIds;
    }

}
