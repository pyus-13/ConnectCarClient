package com.ai.mycar.notification;

import android.util.Log;

import com.ai.mycar.rx.RegistrationManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

/**
 * Created by TCS Mumbai Mobility
 * User :   Piyush Agarwal 559339(dksc102950).
 * Date :   1/10/17
 * Time :   6:07 PM
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private RegistrationManager mRegistrationManager = RegistrationManager.getInstance();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        //
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        //The message which i send will have keys named [message, image, AnotherActivity] and corresponding values.
        //You can change as per the requirement.

        //message will contain the Push Message
        String message = remoteMessage.getData().get("message");

        String actionId = remoteMessage.getData().get("actionId");

        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setActionId(Integer.parseInt(actionId));
        notificationMessage.setMessage(message);

        //imageUri will contain URL of the image to be displayed with Notification
        //If the key AnotherActivity has  value as True then when the user taps on notification, in the app AnotherActivity will be opened.
        //If the key AnotherActivity has  value as False then when the user taps on notification, in the app MainActivity will be opened.

        mRegistrationManager.setNotificationMessage(notificationMessage);

        Log.d("", "Message Received " + message);
    }
}
