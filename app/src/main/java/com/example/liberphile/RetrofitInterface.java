package com.example.liberphile;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<ResponseBody> executeLogin(@Body LoginResult loginResult);

    @POST("/singup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

}
