package pe.adiazc.personas.servicio;



import retrofit2.Retrofit;

public class UinamesServicio {

    final String URL_BASE = "https://uinames.com/api/" ;

    // Intaciamo un servicio base
    public Retrofit make(){
        return  (new ServicioManager()).oServicio(URL_BASE);
    }

}
