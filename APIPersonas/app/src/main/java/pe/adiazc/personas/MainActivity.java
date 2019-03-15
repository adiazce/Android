package pe.adiazc.personas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.adiazc.personas.adapters.PersonaAdapter;
import pe.adiazc.personas.dto.Persona;
import pe.adiazc.personas.http.request.PersonaRequest;
import pe.adiazc.personas.servicio.UinamesServicio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit ;
    TextView txtVer ;
    List<Persona> items;
    PersonaAdapter adapter ;
    RecyclerView recyclerView ;
    Spinner spinner ;
    PersonaRequest request;
    String[] strFrutas = new String[] {"5","10","20","50", "100", "300", "500" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVer = findViewById(R.id.tvRegistro);
        recyclerView = findViewById(R.id.rvListaEmpleado);
        spinner = findViewById(R.id.spRegistros);
        // llenar spinner
        llenarSpinner();


        retrofit =  ( new UinamesServicio() ) .make();
        request = retrofit.create(PersonaRequest.class);

        this.EventoChangeSpinner();
    }
    public void EventoChangeSpinner(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Call<List<Persona>> responseCall = request.ObtenerPersonas(Integer.valueOf(strFrutas[position])  );

                responseCall.enqueue(new Callback<List<Persona>>() {
                    @Override
                    public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {

                        if(response.isSuccessful()){
                            items = response.body();
                            adapter = new PersonaAdapter(items);

                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager( new LinearLayoutManager(MainActivity.this    ));
                        }else{
                            Toast.makeText(MainActivity.this,"Ocurrio un Error!.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Persona>> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Ocurrio un Error!.",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void llenarSpinner(){

        ArrayAdapter<String> array = new  ArrayAdapter< >(this,android.R.layout.simple_spinner_item, strFrutas);
        spinner.setAdapter(array);
    }
}
