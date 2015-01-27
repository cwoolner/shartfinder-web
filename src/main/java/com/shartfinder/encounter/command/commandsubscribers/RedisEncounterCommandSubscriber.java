package com.shartfinder.encounter.command.commandsubscribers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.shartfinder.encounter.command.framework.EncounterCommandType;
import com.shartfinder.framework.command.Command;
import com.shartfinder.framework.command.CommandSubscriber;

@Profile("prod")
@Component
public class RedisEncounterCommandSubscriber implements
        CommandSubscriber<EncounterCommandType> {

    @Override
    public void receive(Command<EncounterCommandType> command) {
        // TODO: publish command to Redis
    }

}
