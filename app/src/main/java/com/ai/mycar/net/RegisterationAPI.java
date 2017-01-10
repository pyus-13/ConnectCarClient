package com.ai.mycar.net;

import com.ai.mycar.model.APIResponse;
import com.ai.mycar.model.DeviceInfo;
import com.ai.mycar.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by TCS Mumbai Mobility
 * User :   Piyush Agarwal 559339(dksc102950).
 * Date :   1/10/17
 * Time :   12:55 AM
 */
public interface RegisterationAPI {


    @POST("ai/user")
    Call<APIResponse> createUser(@Body User user);

    @POST("ai/device")
    Call<APIResponse> registerDevice(@Body DeviceInfo deviceInfo);



}
