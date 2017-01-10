package com.ai.mycar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.ai.mycar.model.APIResponse;
import com.ai.mycar.model.User;
import com.ai.mycar.net.NetworkCommunicator;
import com.ai.mycar.rx.RegistrationManager;

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

    private Subscription userSubscription;

    private Unbinder mUnbinder;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

         mUnbinder = ButterKnife.bind(this);


        mNetworkCommunicator = new NetworkCommunicator();

        mRegistrationManager = RegistrationManager.getInstance();


        String s = nameEditText.getText().toString();


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

                Toast.makeText(MainActivity.this, ""+apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        
    }

    @Override
    protected void onResume() {
        super.onResume();

        subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mUnbinder.unbind();
    }
}
