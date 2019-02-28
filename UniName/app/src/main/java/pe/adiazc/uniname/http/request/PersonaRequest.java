package pe.adiazc.uniname.http.request;

import java.util.List;

import pe.adiazc.uniname.dto.Persona;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PersonaRequest {

    @GET("?ext")
    Call<List<Persona>> ObtenerPersonas(@Query("amount")int amount   );

}
