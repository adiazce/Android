package pe.ladrilloslark.jobs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText  etSearch;
    Button btSearch;
    RecyclerView rvJobs;
    JobAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         etSearch = findViewById(R.id.etSearch);
         btSearch =  findViewById(R.id.btSearch);
         rvJobs = findViewById(R.id.rvJobs);

         btSearch.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                     searchJobs();
                 }


         });

    }


    public void searchJobs(){
        String buscar = etSearch.getText().toString();

        JobsRequest jobsRequest = (new ServicioManager()).make().create(JobsRequest.class);
        Call<List<Job>> searchJob = jobsRequest.searchJob(buscar);

        searchJob.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if(response.isSuccessful()){
                    List<Job> jobList = response.body();
                    adapter = new JobAdapter(jobList);
                    rvJobs.setAdapter(adapter);
                    rvJobs.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.d("JobsRequest",t.getMessage());
            }
        });

    }
}
