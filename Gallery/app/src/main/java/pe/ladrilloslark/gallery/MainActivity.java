package pe.ladrilloslark.gallery;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGallery = findViewById(R.id.btGallery);

        btGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              openCamara();
            }
        });
    }

    private void openCamara() {
        final  int  REQUEST_IMAGE_CAPTURE  = 1;
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {



            Toast.makeText(this,"No tiene Permisos",Toast.LENGTH_SHORT).show();

            requestPermissions();

        }else{

            Toast.makeText(this,"Si tiene Permisos",Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             if(intent.resolveActivity(getPackageManager()) != null){
                 startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
             }
             // startActivity(intent);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {


        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 0){
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamara();
                }

        }

    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions( this,new String[]{Manifest.permission.CAMERA},0);
    }
}
