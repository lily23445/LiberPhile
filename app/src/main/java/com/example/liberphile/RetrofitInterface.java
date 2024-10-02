package com.example.liberphile;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<ResponseBody> executeLogin(@Body LoginResult loginResult);

    @POST("/singup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @GET("/books")
    Call<List<Books>> getBooks();

}
