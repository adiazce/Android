package pe.ladrilloslark.pokemonapi.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceAdapter {


    private static String URL_BASE = "https://pokeapi.co/api/v2/";
    private static ApiService API_SERVICE;

    public static ApiService getApiService() {

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API_SERVICE = retrofit.create(ApiService.class);

        }
        return API_SERVICE;
    }


}
