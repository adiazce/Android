package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FizzBuzzActivity extends AppCompatActivity {
    EditText etNumber ;
    Button btExecutar ;
    TextView tvMessage;
    FizzBuzz fizzBuzz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fizz_buzz);

        etNumber = findViewById(R.id.etNumber);
        btExecutar = findViewById(R.id.btExecute);
        tvMessage = findViewById(R.id.tvMessage);
        fizzBuzz = new FizzBuzz();

        btExecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(etNumber.getText().toString()) ;
                String msg = "" ;

                msg =  fizzBuzz.execute(number);

                tvMessage.setText(msg);
            }
        });
    }
}
