package com.ai.mycar.net;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.ai.mycar.model.DeviceInfo;

/**
 * Created by TCS Mumbai Mobility
 * User :   Piyush Agarwal 559339(dksc102950).
 * Date :   1/10/17
 * Time :   6:59 PM
 */
public class RegisterUtil {

    public static void sendRegistrationToServer(Context context, String refreshedToken) {


        NetworkCommunicator networkCommunicator = NetworkCommunicator.getInstance();

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDevicePushToken(refreshedToken);
        deviceInfo.setUserId("abcd");

        networkCommunicator.registerDevice(deviceInfo);

    }


    private static String getDeviceId(Context context) {

        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();


    }
}
