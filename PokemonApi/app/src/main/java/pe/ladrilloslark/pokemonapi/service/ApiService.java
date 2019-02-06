package pe.ladrilloslark.pokemonapi.service;

import pe.ladrilloslark.pokemonapi.modelo.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("pokemon/{name}")
    Call<Pokemon> buscarPokemon(@Path("name") String name);
}
