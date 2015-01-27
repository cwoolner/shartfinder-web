package com.shartfinder.framework.pushnotifier;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.shartfinder.pushnotifications.EncounterUpdatedPushNotification;
import com.shartfinder.pushnotifications.OpenEncounterForUserPushNotification;

@Component
public class InMemoryAsyncPushNotificationPublisher implements PushNotificationPublisher {

    private final PushNotificationHandler<OpenEncounterForUserPushNotification> openEncounterForUserPushNotificationHandler;

    private final PushNotificationHandler<EncounterUpdatedPushNotification> encounterUpdatedPushNotificationHandler;

    @Inject
    public InMemoryAsyncPushNotificationPublisher(
            PushNotificationHandler<OpenEncounterForUserPushNotification> openEncounterForUserPushNotificationHandler,
            PushNotificationHandler<EncounterUpdatedPushNotification> encounterUpdatedPushNotificationHandler) {
        this.openEncounterForUserPushNotificationHandler = openEncounterForUserPushNotificationHandler;
        this.encounterUpdatedPushNotificationHandler = encounterUpdatedPushNotificationHandler;
    }

    @Async
    @Override
    public void publish(PushNotification pushNotification) {
        switch (pushNotification.getType()) {
        case OpenEncounterForUser:
            openEncounterForUserPushNotificationHandler
                    .handle((OpenEncounterForUserPushNotification) pushNotification);
            break;
        case EncounterUpdated:
            encounterUpdatedPushNotificationHandler
                    .handle((EncounterUpdatedPushNotification) pushNotification);
            break;
        default:
            throw new IllegalArgumentException(
                    "Push Notification Type cannot be handled: "
                            + pushNotification.getType());
        }
    }

}
