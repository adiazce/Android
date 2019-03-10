package pe.ladrilloslark.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationActivity extends AppCompatActivity {
    ImageView ivImage;
    Button btZoom,btClock,btFade,btMove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ivImage = findViewById(R.id.ivImage);
        btZoom = findViewById(R.id.btZoom);
        btClock = findViewById(R.id.btClock);
        btFade = findViewById(R.id.btFade);
        btMove = findViewById(R.id.btMove);
        btZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.zoom);
                ivImage.startAnimation(animation);


            }
        });

        btClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.clockwise);
                ivImage.startAnimation(animation);
                btClock.startAnimation(animation);
            }
        });
        btFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.fade);
                ivImage.startAnimation(animation);
                btFade.startAnimation(animation);
            }
        });

        btMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.move);
                ivImage.startAnimation(animation);
                btMove.startAnimation(animation);
            }
        });
    }
}
