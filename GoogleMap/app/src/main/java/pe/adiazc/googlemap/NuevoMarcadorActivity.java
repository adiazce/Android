package pe.adiazc.googlemap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NuevoMarcadorActivity extends AppCompatActivity {

    EditText etNombre, etLat,etLog,etDescripcion;
    Button btGuardar ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_marcador);
        etNombre = findViewById(R.id.etNombre);
        etLat = findViewById(R.id.etLat);
        etLog = findViewById(R.id.etLog);
        etDescripcion = findViewById(R.id.etDescripcion);
        btGuardar = findViewById(R.id.btnGuardar);
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarMarcador();
            }
        });
    }


    public void GuardarMarcador(){
        Intent replyIntent = new Intent();

        if(TextUtils.isEmpty(etNombre.getText()) && TextUtils.isEmpty(etLat.getText()) &&
                TextUtils.isEmpty(etLog.getText()) && TextUtils.isEmpty(etDescripcion.getText())
        ){
            setResult(RESULT_CANCELED,replyIntent);
        }else {
            String nombre = etNombre.getText().toString();
            String lat =    etLat.getText().toString()   ;
            String log =  etLog.getText().toString()   ;
            String desc = etDescripcion.getText().toString();
            replyIntent.putExtra("nombre",nombre);
            replyIntent.putExtra("lat",lat);
            replyIntent.putExtra("log",log);
            replyIntent.putExtra("desc",desc);
            Log.i(" NuevoMarcadorLat",lat);
            setResult(RESULT_OK,replyIntent);

        }
        finish();

    }
}
