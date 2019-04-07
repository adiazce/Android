package com.example.moviesef;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    EditText etSearch ;
    ImageButton btSearch ;
    RecyclerView rvMovies ;
    public  final  String API_KEY = "3cae426b920b29ed2fb1c0749f258325";
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        etSearch = view.findViewById(R.id.etMovie);
        btSearch = view.findViewById(R.id.btnSearch);
        rvMovies = view.findViewById(R.id.rvSearch);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String search = etSearch.getText().toString();
                Retrofit retrofit =  ( new  MovieService() ).make();

                MovieRequest movieRequest = retrofit.create(MovieRequest.class);
                Call<MovieResponse> searchMethod =  movieRequest.searchMovie(API_KEY,search);
                searchMethod.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if(response.isSuccessful()){
                            MovieResponse mov = response.body();
                            MovieAdapter adapter = new MovieAdapter(mov.getResults());
                            rvMovies.setAdapter(adapter);
                            rvMovies.setLayoutManager( new LinearLayoutManager(view.getContext()));
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });


            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
