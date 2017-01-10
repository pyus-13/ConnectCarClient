package com.ai.mycar.model;

/**
 * Created by Piyush Agarwal on 1/7/17.
 */
public class DeviceInfo {

    private String userId;

    private String devicePushToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDevicePushToken() {
        return devicePushToken;
    }

    public void setDevicePushToken(String devicePushToken) {
        this.devicePushToken = devicePushToken;
    }


    @Override
    public String toString() {
        return "DeviceInfo{" +
                "userId='" + userId + '\'' +
                ", devicePushToken='" + devicePushToken + '\'' +
                '}';
    }
}
