package com.shartfinder.framework.pushnotifier;

public interface PushNotificationHandler<T extends PushNotification> {

    void handle(T pushNotification);

}
