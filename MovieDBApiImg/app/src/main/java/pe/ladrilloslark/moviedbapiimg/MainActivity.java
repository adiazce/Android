package pe.ladrilloslark.moviedbapiimg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> items ;
    MovieAdapter movieAdapter;

    EditText etMovie;

    RecyclerView rvMovie;

    Button btSearch ;
    final String URL = "https://api.themoviedb.org/3/search/";


    public void searchMovies() {
        String apiKey = "3cae426b920b29ed2fb1c0749f258325";
        final String movie = etMovie.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieInterface movieInterface = retrofit.create(MovieInterface.class);
        Call<MovieResponse> searchMethod = movieInterface.searchMovies(apiKey,movie);

        searchMethod.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.d("response",response.code()+"");

                    MovieResponse movieResponse =   response.body();
                    items = movieResponse.getMovies();
                    movieAdapter = new MovieAdapter(items);
                    rvMovie.setAdapter(movieAdapter);
                    rvMovie.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),""+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("MainActivity",t.toString());
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         etMovie = findViewById(R.id.etMovie);
        rvMovie = findViewById(R.id.rvMovie);
        btSearch = findViewById(R.id.btSearch);

        items = new ArrayList<>();
        movieAdapter = new MovieAdapter(items);
        rvMovie.setAdapter(movieAdapter);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovies();
            }
        });
    }
}
