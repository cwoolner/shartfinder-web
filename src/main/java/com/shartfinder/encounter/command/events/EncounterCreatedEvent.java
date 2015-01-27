package com.shartfinder.encounter.command.events;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shartfinder.encounter.command.framework.EncounterEvent;
import com.shartfinder.encounter.command.framework.EncounterEventType;
import com.shartfinder.framework.event.BaseEvent;

public class EncounterCreatedEvent extends BaseEvent<EncounterEventType> implements
        EncounterEvent {

    private static final EncounterEventType TYPE = EncounterEventType.EncounterCreated;

    private final Set<UUID> playerIds;

    @JsonCreator
    public EncounterCreatedEvent(@JsonProperty(value = "aggregateId") UUID aggregateId,
            @JsonProperty(value = "playerIds") Set<UUID> playerIds) {
        super(aggregateId, TYPE);
        this.playerIds = playerIds;
    }

    public Set<UUID> getPlayerIds() {
        return playerIds;
    }

}
