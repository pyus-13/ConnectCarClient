package com.ai.mycar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ai.mycar.model.APIResponse;
import com.ai.mycar.model.User;
import com.ai.mycar.net.NetworkCommunicator;
import com.ai.mycar.net.RegisterUtil;
import com.ai.mycar.rx.RegistrationManager;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.name)
    EditText nameEditText;


    private NetworkCommunicator mNetworkCommunicator;

    private RegistrationManager mRegistrationManager;

    private Subscription userSubscription , deviceRegisterSubs;

    private Unbinder mUnbinder;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

         mUnbinder = ButterKnife.bind(this);


        mNetworkCommunicator = NetworkCommunicator.getInstance();

        mRegistrationManager = RegistrationManager.getInstance();

        nameEditText = (EditText) findViewById(R.id.name);

        ImageButton nextButton = (ImageButton) findViewById(R.id.next);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();

            }
        });



    }

    @OnClick(R.id.next)
    public void registerUser() {
        
        mNetworkCommunicator.registerUser(new User(nameEditText.getText().toString()));
    }


    private void subscribe() {
        
        userSubscription = mRegistrationManager.getRegisterUserObservable().subscribe(new Observer<APIResponse>() {
            
            @Override
            public void onCompleted() {
                
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(APIResponse apiResponse) {

                if (apiResponse != null) {

                    String token = FirebaseInstanceId.getInstance().getToken();

                    Toast.makeText(MainActivity.this, "" + token, Toast.LENGTH_SHORT).show();

                    RegisterUtil.sendRegistrationToServer(MainActivity.this, token);


                }

            }
        });
        
    }


    private void subscribeDevice() {

        deviceRegisterSubs = mRegistrationManager.getRegisterDeviceObservable().subscribe(new Observer<APIResponse>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(APIResponse apiResponse) {

                if (apiResponse != null) {

                    startActivity(new Intent(MainActivity.this, MyCarActivity.class));

                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        subscribe();

        subscribeDevice();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mUnbinder.unbind();

        unsubscribe();
    }

    private void unsubscribe() {

        if (userSubscription != null) {

            userSubscription.unsubscribe();
        }

        if (deviceRegisterSubs != null) {

            deviceRegisterSubs.unsubscribe();
        }
    }


}
