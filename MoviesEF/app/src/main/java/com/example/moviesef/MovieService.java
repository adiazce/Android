package com.example.moviesef;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieService {

    private final String URL_BASE = "https://api.themoviedb.org/";


    public Retrofit make()
    {
        return new Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
