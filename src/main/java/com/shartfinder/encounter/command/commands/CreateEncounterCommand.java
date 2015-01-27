package com.shartfinder.encounter.command.commands;

import java.util.Set;
import java.util.UUID;

import com.shartfinder.encounter.command.framework.EncounterCommand;
import com.shartfinder.encounter.command.framework.EncounterCommandType;
import com.shartfinder.framework.command.BaseCommand;

public class CreateEncounterCommand extends BaseCommand<EncounterCommandType> implements
        EncounterCommand {

    private static final EncounterCommandType TYPE = EncounterCommandType.CreateEncounter;

    private final Set<UUID> playerIds;

    public CreateEncounterCommand(Set<UUID> playerIds) {
        super(TYPE);
        this.playerIds = playerIds;
    }

    public Set<UUID> getPlayerIds() {
        return playerIds;
    }

}
