package com.ai.mycar.rx;

import com.ai.mycar.model.APIResponse;
import com.ai.mycar.notification.NotificationMessage;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Created by TCS Mumbai Mobility
 * User :   Piyush Agarwal 559339(dksc102950).
 * Date :   1/10/17
 * Time :   1:09 AM
 */
public class RegistrationManager {

    private static RegistrationManager instance;

    private BehaviorSubject<APIResponse> mUserBehaviorSubject;

    private BehaviorSubject<APIResponse> mDeviceRegisterBehaviourSubject;

    private PublishSubject<NotificationMessage> mNotificationMessagePublishSubject;


    public RegistrationManager() {

        mUserBehaviorSubject = BehaviorSubject.create();

        mDeviceRegisterBehaviourSubject = BehaviorSubject.create();

        mNotificationMessagePublishSubject = PublishSubject.create();

    }

    public static RegistrationManager getInstance() {

        if (instance == null) {

            synchronized (RegistrationManager.class) {
                if (null == instance) {
                    instance = new RegistrationManager();
                }
            }

        }

        return instance;
    }


    public Observable<APIResponse> getRegisterUserObservable() {
        try {
            return mUserBehaviorSubject.observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
                @Override
                public void call() {
                    mUserBehaviorSubject.last();
                }
            });
        }
        catch (ExceptionInInitializerError e) {

            return null;
        }
    }


    public Observable<NotificationMessage> getNotificationObservable() {
        try {
            return mNotificationMessagePublishSubject.observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
                @Override
                public void call() {

                    mNotificationMessagePublishSubject.last();

                }
            });
        }
        catch (ExceptionInInitializerError e) {

            return null;
        }
    }



    public Observable<APIResponse> getRegisterDeviceObservable() {
        try {
            return mDeviceRegisterBehaviourSubject.observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
                @Override
                public void call() {
                    mDeviceRegisterBehaviourSubject.last();
                }
            });
        }
        catch (ExceptionInInitializerError e) {

            return null;
        }
    }


    public void setUserResponse(APIResponse response) {

        mUserBehaviorSubject.onNext(response);
    }

    public void setDeviceRegisterResponse(APIResponse registerResponse) {

        mDeviceRegisterBehaviourSubject.onNext(registerResponse);

    }


    public void setNotificationMessage(NotificationMessage notificationMessage) {

        mNotificationMessagePublishSubject.onNext(notificationMessage);

    }
}
