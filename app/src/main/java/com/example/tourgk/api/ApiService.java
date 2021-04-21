package com.example.tourgk.api;

import com.example.tourgk.model.Tour;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/tours/1/9")
    Call<Tour> getTours();
}
