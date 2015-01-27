package com.shartfinder.pushnotificationhandlers;

import javax.inject.Inject;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.shartfinder.framework.pushnotifier.PushNotificationHandler;
import com.shartfinder.login.query.repository.LoginRepository;
import com.shartfinder.pushnotifications.OpenEncounterForUserPushNotification;
import com.shartfinder.util.MessagingConstants;

@Component
public class OpenEncounterForUserPushNotificationHandler implements
        PushNotificationHandler<OpenEncounterForUserPushNotification> {

    private final LoginRepository loginRepository;

    private final SimpMessageSendingOperations messagingTemplate;

    @Inject
    public OpenEncounterForUserPushNotificationHandler(LoginRepository loginRepository,
            SimpMessageSendingOperations messagingTemplate) {
        this.loginRepository = loginRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Async
    @Override
    public void handle(OpenEncounterForUserPushNotification pushNotification) {
        String username = loginRepository.fetchUsernameByAggregateId(pushNotification
                .getPlayerId());
        messagingTemplate.convertAndSendToUser(username,
                MessagingConstants.OPEN_ENCOUNTER_FOR_USER,
                pushNotification.getEncounterId());
    }

}
