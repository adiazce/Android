package pe.adiazc.busroute.servicio;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SevicioManager {

    private  static  String URL_BASE = "https://api.wmata.com/" ;

    Retrofit retrofit ;

    public Retrofit make ( ){
        retrofit =  new Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();
        return  retrofit;
    }



}
