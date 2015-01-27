package com.shartfinder.pushnotifications;

import java.util.UUID;

import javax.inject.Inject;

import com.shartfinder.framework.pushnotifier.BasePushNotification;
import com.shartfinder.framework.pushnotifier.PushNotificationType;

public class EncounterUpdatedPushNotification extends BasePushNotification {

    private static final PushNotificationType TYPE = PushNotificationType.EncounterUpdated;

    private final UUID encounterId;

    @Inject
    public EncounterUpdatedPushNotification(UUID encounterId) {
        super(TYPE);
        this.encounterId = encounterId;
    }

    public UUID getEncounterId() {
        return encounterId;
    }

}
