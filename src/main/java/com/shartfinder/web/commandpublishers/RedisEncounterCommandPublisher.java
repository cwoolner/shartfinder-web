package com.shartfinder.web.commandpublishers;

import javax.inject.Inject;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.shartfinder.encounter.command.framework.EncounterCommand;
import com.shartfinder.encounter.command.framework.EncounterCommandType;
import com.shartfinder.framework.command.Command;
import com.shartfinder.framework.command.CommandPublisher;

@Profile("prod")
@Component
public class RedisEncounterCommandPublisher implements
        CommandPublisher<EncounterCommandType> {

    private final RedisTemplate<String, EncounterCommand> redisTemplateEncounterCommand;

    @Lazy
    @Inject
    public RedisEncounterCommandPublisher(
            RedisTemplate<String, EncounterCommand> redisTemplateEncounterCommand) {
        this.redisTemplateEncounterCommand = redisTemplateEncounterCommand;
    }

    @Override
    public void publish(Command<EncounterCommandType> command) {
        switch (command.getType()) {
        case CreateEncounter:
            redisTemplateEncounterCommand.convertAndSend("create-encounter", command);
            break;
        case RollInitiative:
            redisTemplateEncounterCommand.convertAndSend("roll-initiative", command);
            break;
        default:
            break;
        }

    }
}
