package com.example.moviesef;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.PrototypeFavorite> {


    List<Movie> items;


    public FavoriteAdapter(List<Movie> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public PrototypeFavorite onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_prototype,viewGroup,false);


        return new PrototypeFavorite(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrototypeFavorite prototypeFavorite, int i) {
        final Movie movie = items.get(i);
        prototypeFavorite.tvTitle.setText(movie.getTitle());
        prototypeFavorite.tvDescription.setText(movie.getOverview());
        prototypeFavorite.id = movie.getId();

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class PrototypeFavorite  extends RecyclerView.ViewHolder {
          int  id ;
        TextView tvTitle, tvDescription;
        ImageButton btnDeleteFavorito ;
        public PrototypeFavorite(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnDeleteFavorito = itemView.findViewById(R.id.btnDeleteFavorite);

             btnDeleteFavorito.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                        Movie movie = new Movie();
                        movie.setId(id);
                        DataBase.getDatabase(v.getContext()).movieDao().delete(movie);
                 }
             });
        }

    }
}
