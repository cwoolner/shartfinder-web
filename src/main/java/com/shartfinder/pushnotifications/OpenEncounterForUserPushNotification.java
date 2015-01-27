package com.shartfinder.pushnotifications;

import java.util.UUID;

import javax.inject.Inject;

import com.shartfinder.framework.pushnotifier.BasePushNotification;
import com.shartfinder.framework.pushnotifier.PushNotificationType;

public class OpenEncounterForUserPushNotification extends BasePushNotification {

    private static final PushNotificationType TYPE = PushNotificationType.OpenEncounterForUser;

    private final UUID encounterId;

    private final UUID playerId;

    @Inject
    public OpenEncounterForUserPushNotification(UUID encounterId, UUID playerId) {
        super(TYPE);
        this.encounterId = encounterId;
        this.playerId = playerId;
    }

    public UUID getEncounterId() {
        return encounterId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

}
