package com.shartfinder.encounter.query.handlers;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.shartfinder.core.api.chat.SendChatMessageCommand;
import com.shartfinder.encounter.command.events.InitiativeOrderCreatedEvent;
import com.shartfinder.encounter.query.repository.EncounterRepository;
import com.shartfinder.framework.event.EventHandler;
import com.shartfinder.framework.pushnotifier.PushNotification;
import com.shartfinder.framework.pushnotifier.PushNotificationPublisher;
import com.shartfinder.pushnotifications.EncounterUpdatedPushNotification;
import com.shartfinder.web.model.EncounterViewModel;

@Component
public class InitiativeOrderUpdatedEventHandler implements
        EventHandler<InitiativeOrderCreatedEvent> {

    private final EncounterRepository encounterRepository;

    private final SendChatMessageCommand sendChatMessageCommand;

    private final PushNotificationPublisher pushNotificationPublisher;

    @Inject
    public InitiativeOrderUpdatedEventHandler(EncounterRepository encounterRepository,
            SendChatMessageCommand sendChatMessageCommand,
            PushNotificationPublisher pushNotificationPublisher) {
        this.encounterRepository = encounterRepository;
        this.sendChatMessageCommand = sendChatMessageCommand;
        this.pushNotificationPublisher = pushNotificationPublisher;
    }

    @Override
    public void handle(InitiativeOrderCreatedEvent event) {
        handleUpdatingEncounterViewModel(event);
        handlePushNotifications(event);
        handleChat();
    }

    private void handleUpdatingEncounterViewModel(InitiativeOrderCreatedEvent event) {
        EncounterViewModel currentEncounterViewModel = encounterRepository
                .fetchById(event.getAggregateId());
        // TODO: update the list in the view model
        EncounterViewModel updatedEncounterViewModel = new EncounterViewModel(
                currentEncounterViewModel.getEncounterId());
        encounterRepository.save(updatedEncounterViewModel);
    }

    private void handlePushNotifications(InitiativeOrderCreatedEvent event) {
        PushNotification pushNotification = new EncounterUpdatedPushNotification(
                event.getAggregateId());
        pushNotificationPublisher.publish(pushNotification);
    }

    private void handleChat() {
        String message = "Initiative order updated";
        sendChatMessageCommand.execute(message, true, null);
    }

}
