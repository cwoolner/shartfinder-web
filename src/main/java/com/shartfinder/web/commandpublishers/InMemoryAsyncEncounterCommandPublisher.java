package com.shartfinder.web.commandpublishers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.shartfinder.encounter.command.framework.EncounterCommandType;
import com.shartfinder.framework.command.Command;
import com.shartfinder.framework.command.CommandPublisher;

@Profile("default")
@Component
public class InMemoryAsyncEncounterCommandPublisher implements
        CommandPublisher<EncounterCommandType> {

    @Override
    public void publish(Command<EncounterCommandType> command) {
        // do nothing since this project does not include any command handlers
    }

}
