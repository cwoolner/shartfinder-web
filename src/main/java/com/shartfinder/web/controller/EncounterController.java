package com.shartfinder.web.controller;

import java.security.Principal;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.shartfinder.encounter.command.commands.RollInitiativeCommand;
import com.shartfinder.encounter.command.framework.EncounterCommandType;
import com.shartfinder.encounter.query.repository.EncounterRepository;
import com.shartfinder.framework.command.CommandPublisher;
import com.shartfinder.login.query.repository.LoginRepository;
import com.shartfinder.web.model.EncounterViewModel;
import com.shartfinder.web.model.RollDiceViewModel;

@Controller
public class EncounterController {

    private final CommandPublisher<EncounterCommandType> commandPublisher;

    private final LoginRepository loginRepository;

    private final EncounterRepository encounterRepository;

    @Inject
    public EncounterController(CommandPublisher<EncounterCommandType> commandPublisher,
            LoginRepository loginRepository, EncounterRepository encounterRepository) {
        this.commandPublisher = commandPublisher;
        this.loginRepository = loginRepository;
        this.encounterRepository = encounterRepository;
    }

    @SubscribeMapping(value = "/topic/encounter/{encounterId}")
    public EncounterViewModel fetchEncounter(@DestinationVariable UUID encounterId) {
        return encounterRepository.fetchById(encounterId);
    }

    @MessageMapping(value = "/app/rolldice")
    public void check(RollDiceViewModel rollDiceViewModel, Principal principal) {
        UUID playerId = loginRepository.fetchAggregateIdByUsername(principal.getName());
        commandPublisher.publish(new RollInitiativeCommand(rollDiceViewModel
                .getEncounterId(), playerId, rollDiceViewModel.getDiceRoll()));
    }

}
