package pe.adiazc.uniname.servicio;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.adiazc.uniname.adapters.PersonaAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioManager {

    Retrofit retrofit;

    // crea una intacia de servicio
    public Retrofit oServicio(String url_base) {

        Gson gson = new GsonBuilder().create();
        Log.d("Servicio ", url_base);
        retrofit = new Retrofit.
                Builder().
                baseUrl(url_base).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        return retrofit;
    }


}
