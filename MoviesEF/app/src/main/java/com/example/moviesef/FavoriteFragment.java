package com.example.moviesef;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    List<Movie> movie;
    RecyclerView rvFavorite;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);;
        rvFavorite = view.findViewById(R.id.rvFavorite);

        movie = DataBase.getDatabase(view.getContext()).movieDao().getAll();
        FavoriteAdapter adapter = new FavoriteAdapter(movie);
        rvFavorite.setAdapter(adapter);
        rvFavorite.setLayoutManager(new LinearLayoutManager(view.getContext()));


        return view;
    }

}
