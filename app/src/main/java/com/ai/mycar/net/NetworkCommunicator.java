package com.ai.mycar.net;

import com.ai.mycar.model.APIResponse;
import com.ai.mycar.model.DeviceInfo;
import com.ai.mycar.model.User;
import com.ai.mycar.rx.RegistrationManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TCS Mumbai Mobility
 * User :   Piyush Agarwal 559339(dksc102950).
 * Date :   1/10/17
 * Time :   12:56 AM
 */
public class NetworkCommunicator {

    private static NetworkCommunicator instance;

    private RegisterationAPI mRegisterationAPI;

    private RegistrationManager mRegistrationManager;


    public static NetworkCommunicator getInstance() {

        if (instance == null) {

            synchronized (NetworkCommunicator.class) {
                if (null == instance) {
                    instance = new NetworkCommunicator();
                }
            }

        }

        return instance;

    }


    private NetworkCommunicator() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://connect-car.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mRegisterationAPI = retrofit.create(RegisterationAPI.class);

        mRegistrationManager = RegistrationManager.getInstance();

    }


    public void registerUser(User user) {

        Call<APIResponse> userRequest = mRegisterationAPI.createUser(user);

        userRequest.enqueue(new Callback<APIResponse>() {

            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                mRegistrationManager.setUserResponse(response.body());

            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable throwable) {

            }
        });
    }


    public void registerDevice(DeviceInfo device) {

        Call<APIResponse> deviceRegisterRequest = mRegisterationAPI.registerDevice(device);

        deviceRegisterRequest.enqueue(new Callback<APIResponse>() {

            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                mRegistrationManager.setDeviceRegisterResponse(response.body());

            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable throwable) {

            }
        });
    }
}
