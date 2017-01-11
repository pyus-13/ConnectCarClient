package com.ai.mycar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.ai.mycar.notification.ActionsOnCar;
import com.ai.mycar.notification.NotificationMessage;
import com.ai.mycar.rx.RegistrationManager;

import rx.Observer;
import rx.Subscription;

public class MyCarActivity extends AppCompatActivity {

    private ImageView thermostatStatus;
    private ImageView musicStatus;

    private Subscription notificationSubscription;

    private RegistrationManager mRegistrationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car);

        thermostatStatus = (ImageView) findViewById(R.id.statusThermostat);

        musicStatus = (ImageView) findViewById(R.id.statusMusic);

        mRegistrationManager = RegistrationManager.getInstance();


    }

    @Override
    protected void onResume() {
        super.onResume();
        subscribe();
    }

    private void subscribe() {

        notificationSubscription = mRegistrationManager.getNotificationObservable().subscribe(new Observer<NotificationMessage>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NotificationMessage notificationMessage) {

                if (notificationMessage != null) {

                    toggleSwitch(notificationMessage.getActionId());

                }

            }
        });

    }

    private void toggleSwitch(int actionId) {

        switch (actionId) {

            case ActionsOnCar.THERMOSTAT_ON:

                thermostatStatus.setImageDrawable(getResources().getDrawable(R.drawable.turn_on));

                break;

            case ActionsOnCar.MUSIC_OFF:

                musicStatus.setImageDrawable(getResources().getDrawable(R.drawable.turn_off));


                break;

            case ActionsOnCar.MUSIC_ON:

                musicStatus.setImageDrawable(getResources().getDrawable(R.drawable.turn_on));


                break;

            case ActionsOnCar.THERMOSTAT_OFF:

                thermostatStatus.setImageDrawable(getResources().getDrawable(R.drawable.turn_off));


                break;

            default:

        }
    }


    private void unsubscribe() {

        if (notificationSubscription != null) {

            notificationSubscription.unsubscribe();
        }
    }

}
