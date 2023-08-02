package com.android.demo1.Controllers;

import com.android.demo1.Models.Shoe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiController {
    String DOMAIN = "https://api.shoe.gorokiapp.com/api/";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiController apiService = new Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiController.class);

    @GET("shoes")
    Call<List<Shoe>> getShoes();

    @POST("shoes")
    Call<Shoe> createShoe(@Body() RequestBody shoe);

    @POST("shoes")
    @Multipart
    Call<Shoe> createShoe(@Part("name") RequestBody name, @Part("description") RequestBody description, @Part("price") RequestBody price, @Part MultipartBody.Part img);



}
