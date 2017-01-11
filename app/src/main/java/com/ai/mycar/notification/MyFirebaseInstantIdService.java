package com.ai.mycar.notification;

import android.util.Log;

import com.ai.mycar.net.RegisterUtil;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

/**
 * Created by TCS Mumbai Mobility
 * User :   Piyush Agarwal 559339(dksc102950).
 * Date :   1/10/17
 * Time :   6:11 PM
 */
public class MyFirebaseInstantIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();


        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        RegisterUtil.sendRegistrationToServer(this, refreshedToken);
    }


}
