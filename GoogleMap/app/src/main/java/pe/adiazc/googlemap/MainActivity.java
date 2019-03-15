package pe.adiazc.googlemap;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import pe.adiazc.googlemap.adapter.MarcadorAdapter;

import pe.adiazc.googlemap.entidad.Marcador;

import pe.adiazc.googlemap.viewmodel.MarcadorViewModel;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    RecyclerView rvMarcador;
    Button btnMapa , btnNuevoMarcador;
    private MarcadorViewModel marcadorViewModel;
    MarcadorAdapter adapter ;
    List<Marcador>  lista ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMapa = findViewById(R.id.btnVerMapa);
        btnNuevoMarcador = findViewById(R.id.btnNuevoMarcador);

        rvMarcador = findViewById(R.id.rvMarcador);
        InicializarRvMarcador();
        NuevoMarcadoIr();

        ViewModelProvider.Factory  factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if(modelClass.isAssignableFrom(MarcadorViewModel.class)){
                    return   (T) new MarcadorViewModel( getApplication());
                }

                throw new IllegalArgumentException("Unknown ViewModel class");
            }
        };

        marcadorViewModel = factory.create(MarcadorViewModel.class);

         marcadorViewModel.getAllmarcador().observe(this, new Observer<List<Marcador>>() {
            @Override
            public void onChanged(@Nullable List<Marcador> marcadors) {
                lista = marcadors;
                adapter.setListMarcador(marcadors);
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                ArrayList<Marcador>  listas = new ArrayList<>();
                listas.addAll( lista );

                Log.i("MAIN",listas.size()+"");

                startActivity(intent);
            }
        });

    }


    public void NuevoMarcadoIr(){
        btnNuevoMarcador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NuevoMarcadorActivity.class);

                startActivityForResult(intent , NEW_WORD_ACTIVITY_REQUEST_CODE );


            }
        });
    }


    public void InicializarRvMarcador(){
         this.adapter = new MarcadorAdapter(this);
         rvMarcador.setAdapter(adapter);
         rvMarcador.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK ) {
            Marcador entity = new Marcador();

            entity.setNombre(data.getStringExtra("nombre")+"");
            entity.setLongitud( data.getStringExtra("log")+"" );
            entity.setLatitud( data.getStringExtra("lat")+"");
            entity.setDescripcion(data.getStringExtra("desc")+"");

           marcadorViewModel.Insert(entity);
           Toast.makeText(getApplicationContext(),R.string.messaje_guardado,Toast.LENGTH_SHORT).show();


        }else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
