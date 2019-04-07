package com.example.moviesef;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.PrototypeMovie> {

    List<Movie> items;


    public MovieAdapter(List<Movie> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PrototypeMovie onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_prototype,viewGroup,false);


        return new PrototypeMovie(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrototypeMovie prototypeMovie, int i) {
        Movie movie = items.get(i);
        prototypeMovie.tvTitle.setText(movie.getTitle());
        prototypeMovie.tvDescription.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class PrototypeMovie  extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription;
        ImageButton btnAddFavorito ;
        public PrototypeMovie(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnAddFavorito = itemView.findViewById(R.id.btndeleteFavorite);

            btnAddFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Movie movie = new Movie();
                    movie.setOverview(tvDescription.getText().toString());
                    movie.setTitle(tvTitle.getText().toString());
                    DataBase.getDatabase(v.getContext()).movieDao().insertAll(movie);
                }
            });
        }
    }
}
