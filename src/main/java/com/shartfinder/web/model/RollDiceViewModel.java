package com.shartfinder.web.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RollDiceViewModel {

    private final UUID encounterId;

    private final int diceRoll;

    @JsonCreator
    public RollDiceViewModel(@JsonProperty("encounterId") UUID encounterId,
            @JsonProperty("diceRoll") int diceRoll) {
        this.encounterId = encounterId;
        this.diceRoll = diceRoll;
    }

    public UUID getEncounterId() {
        return encounterId;
    }

    public int getDiceRoll() {
        return diceRoll;
    }
}
