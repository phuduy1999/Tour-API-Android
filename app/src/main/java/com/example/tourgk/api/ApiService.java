package com.example.tourgk.api;

import com.example.tourgk.model.BookingRequest;
import com.example.tourgk.model.Client;
import com.example.tourgk.model.LoginRequest;
import com.example.tourgk.model.Tour;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/tours/{page}/{limit}")
    Call<Tour> getTours(@Path("page") int page, @Path("limit") int limit);

    @GET("api/tours/{id}")
    Call<Tour> getTourById(@Path("id") Long id);

    @POST("api/client/tour")
    Call<String> checkBooking(@Body BookingRequest bo);

    @POST("api/client/booking")
    Call<String> booking(@Body BookingRequest bookingRequest);

    @POST("api/client")
    Call<Client> createAccount(@Body Client client);

    @POST("api/login")
    Call<String> login(@Body LoginRequest lo);

    @POST("api/client/email")
    Call<Client> getClient(@Body Client lo);
}
