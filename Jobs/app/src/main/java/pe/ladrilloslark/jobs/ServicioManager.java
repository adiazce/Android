package pe.ladrilloslark.jobs;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioManager {

    final String URL_BASE = "https://jobs.github.com/positions.json/";
    Retrofit retrofit;

    public Retrofit make() {

        return new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
