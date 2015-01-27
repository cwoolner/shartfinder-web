package com.shartfinder.pushnotificationhandlers;

import javax.inject.Inject;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.shartfinder.encounter.query.repository.EncounterRepository;
import com.shartfinder.framework.pushnotifier.PushNotificationHandler;
import com.shartfinder.pushnotifications.EncounterUpdatedPushNotification;
import com.shartfinder.util.MessagingConstants;
import com.shartfinder.web.model.EncounterViewModel;

@Component
public class EncounterUpdatedPushNotificationHandler implements
        PushNotificationHandler<EncounterUpdatedPushNotification> {

    private final SimpMessageSendingOperations messagingTemplate;

    private final EncounterRepository encounterRepository;

    @Inject
    public EncounterUpdatedPushNotificationHandler(
            SimpMessageSendingOperations messagingTemplate,
            EncounterRepository encounterRepository) {
        this.messagingTemplate = messagingTemplate;
        this.encounterRepository = encounterRepository;
    }

    @Async
    @Override
    public void handle(EncounterUpdatedPushNotification pushNotification) {
        EncounterViewModel encounterViewModel = encounterRepository
                .fetchById(pushNotification.getEncounterId());
        messagingTemplate.convertAndSend(
                String.format(MessagingConstants.ENCOUNTER_STATUS,
                        pushNotification.getEncounterId()), encounterViewModel);
    }

}
