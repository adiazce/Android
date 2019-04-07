package com.example.moviesef;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieRequest {
    @GET("3/search/movie")
    Call<MovieResponse> searchMovie(@Query("api_key") String api_key,@Query("query") String query) ;
}
