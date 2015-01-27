package com.shartfinder.web.commandpublishers;

import javax.inject.Inject;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.shartfinder.encounter.command.framework.EncounterCommandType;
import com.shartfinder.framework.command.Command;
import com.shartfinder.framework.command.CommandPublisher;
import com.shartfinder.framework.command.CommandSubscriber;

@Component
public class InMemoryAsyncEncounterCommandPublisher implements
        CommandPublisher<EncounterCommandType> {

    private final CommandSubscriber<EncounterCommandType> encounterCommandSubscriber;

    @Lazy
    @Inject
    public InMemoryAsyncEncounterCommandPublisher(
            CommandSubscriber<EncounterCommandType> tableCommandSubscriber) {
        this.encounterCommandSubscriber = tableCommandSubscriber;
    }

    @Override
    public void publish(Command<EncounterCommandType> command) {
        encounterCommandSubscriber.receive(command);
    }

}
