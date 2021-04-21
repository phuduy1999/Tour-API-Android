package com.example.tourgk.api;

import com.example.tourgk.model.Tour;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/tours/{page}/{limit}")
    Call<Tour> getTours(@Path("page") int page, @Path("limit") int limit);

    @GET("api/tours/{id}")
    Call<Tour> getTourById(@Path("id") Long id);
}
