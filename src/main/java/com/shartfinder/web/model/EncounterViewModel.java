package com.shartfinder.web.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EncounterViewModel {

    private final UUID encounterId;

    @JsonCreator
    public EncounterViewModel(@JsonProperty("encounterId") UUID encounterId) {
        this.encounterId = encounterId;
    }

    public UUID getEncounterId() {
        return encounterId;
    }

}
