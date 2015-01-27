package com.shartfinder.encounter.command.commands;

import java.util.UUID;

import com.shartfinder.encounter.command.framework.EncounterCommand;
import com.shartfinder.encounter.command.framework.EncounterCommandType;
import com.shartfinder.framework.command.BaseCommand;

public class RollInitiativeCommand extends BaseCommand<EncounterCommandType> implements
        EncounterCommand {

    private static final EncounterCommandType TYPE = EncounterCommandType.RollInitiative;

    private final UUID encounterId;

    private final UUID playerId;

    private final int diceRoll;

    public RollInitiativeCommand(UUID encounterId, UUID playerId, int diceRoll) {
        super(TYPE);
        this.encounterId = encounterId;
        this.playerId = playerId;
        this.diceRoll = diceRoll;
    }

    public UUID getEncounterId() {
        return encounterId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public int getDiceRoll() {
        return diceRoll;
    }

}
