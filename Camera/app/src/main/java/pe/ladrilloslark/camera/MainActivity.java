package pe.ladrilloslark.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.audiofx.EnvironmentalReverb;

import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btCamera;
    ImageView ivPhoto;
    // indica el codigo de quien hizo el pedido
    static final int REQUEST_CAMARE = 1;
    // ruta de la imagen
    String curretPathImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCamera = findViewById(R.id.btncamera);
        ivPhoto = findViewById(R.id.ivPhoto);


        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takePincture();
            }
        });
    }

    private void takePincture() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // si hay un resultado de la camara
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // crea el archivo para la imagen
            File photoFile = null;
            try {
                photoFile = createImage();
            } catch (IOException ex) {
                Log.d("MainActivity", ex.getMessage());
            }
            if(photoFile != null){
                Uri photoUri = FileProvider.getUriForFile(this,"pe.ladrilloslark.camera",photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(takePictureIntent, REQUEST_CAMARE);
            }


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CAMARE && resultCode == RESULT_OK  ) {
            if(data != null){
                Bundle extras = data.getExtras();
                Bitmap image = (Bitmap) extras.get("data");
                ivPhoto.setImageBitmap(image);
            }else{
                Glide.with(this).load(curretPathImage).into(ivPhoto);
            }

        }

    }

    private File createImage() throws IOException {
        // crea el nombre del archivo de la imagen
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String ImageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                ImageFileName,
                ".jpg",
                storageDir
        );
        // graba la imgen

        curretPathImage = image.getAbsolutePath();

        return image;

    }
}
