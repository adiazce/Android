package pe.ladrilloslark.jobs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JobsRequest {
    @GET("?")
    Call<List<Job>> searchJob(@Query("description") String description) ;
}
