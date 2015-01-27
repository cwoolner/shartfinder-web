package com.shartfinder.encounter.query.handlers;

import java.util.UUID;
import java.util.function.Consumer;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.shartfinder.encounter.command.events.EncounterCreatedEvent;
import com.shartfinder.encounter.query.repository.EncounterRepository;
import com.shartfinder.framework.event.EventHandler;
import com.shartfinder.framework.pushnotifier.PushNotification;
import com.shartfinder.framework.pushnotifier.PushNotificationPublisher;
import com.shartfinder.pushnotifications.OpenEncounterForUserPushNotification;
import com.shartfinder.web.model.EncounterViewModel;

@Component
public class EncounterCreatedEventHandler implements EventHandler<EncounterCreatedEvent> {

    private final EncounterRepository encounterRepository;

    private final PushNotificationPublisher pushNotificationPublisher;

    @Inject
    public EncounterCreatedEventHandler(EncounterRepository encounterRepository,
            PushNotificationPublisher pushNotificationPublisher) {
        this.encounterRepository = encounterRepository;
        this.pushNotificationPublisher = pushNotificationPublisher;
    }

    @Override
    public void handle(EncounterCreatedEvent event) {
        handleNewEncounterInsert(event);
        handlePushNotifications(event);
    }

    private void handleNewEncounterInsert(EncounterCreatedEvent event) {
        EncounterViewModel encounterViewModel = new EncounterViewModel(
                event.getAggregateId());
        encounterRepository.save(encounterViewModel);
    }

    private void handlePushNotifications(EncounterCreatedEvent event) {
        Consumer<UUID> openEncounterConsumer = (UUID playerId) -> {
            PushNotification pushNotification = new OpenEncounterForUserPushNotification(
                    event.getAggregateId(), playerId);
            pushNotificationPublisher.publish(pushNotification);
        };
        event.getPlayerIds().forEach(openEncounterConsumer);
    }
}
