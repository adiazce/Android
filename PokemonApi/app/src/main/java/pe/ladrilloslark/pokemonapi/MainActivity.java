package pe.ladrilloslark.pokemonapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pe.ladrilloslark.pokemonapi.modelo.Pokemon;

import pe.ladrilloslark.pokemonapi.service.ServiceAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText etbuscar;
    Button btBuscar;
    TextView tvNombre, tvPeso, tvAltura;
    Retrofit retrofit;
    Pokemon pokemon;
    final String URL_BASE = "https://pokeapi.co/api/v2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etbuscar = findViewById(R.id.etbuscar);
        btBuscar = findViewById(R.id.btbuscar);
        tvNombre = findViewById(R.id.tvNombre);
        tvPeso = findViewById(R.id.tvPeso);
        tvAltura = findViewById(R.id.tvAltura);
        etbuscar.setText("");
        etbuscar.setHint(R.string.buscar);
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscarDatos();
            }
        });

    }

    public void BuscarDatos() {
        Call<Pokemon> call = ServiceAdapter.getApiService().buscarPokemon(etbuscar.getText().toString());
        call.enqueue(new ResponseCallBack());
    }

    private void mostraresultado(Pokemon pokemonResponse) {
        tvNombre.setText(R.string.nombre + pokemonResponse.getName());
        tvPeso.setText( R.string.peso + pokemonResponse.getWeight());
        tvAltura.setText(R.string.altura + pokemonResponse.getHeight());

    }

    class ResponseCallBack implements Callback<Pokemon> {

        @Override
        public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
            if (response.isSuccessful()) {
                Pokemon pokemonResponse = response.body();
                mostraresultado(pokemonResponse);

            }
        }

        @Override
        public void onFailure(Call<Pokemon> call, Throwable t) {
            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}



