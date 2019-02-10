package pe.ladrilloslark.moviedbapiimg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;



class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.LayoutMovie> {

    ArrayList<Movie> movies;

    final String URL_IMG = "https://image.tmdb.org/t/p/w185/";

    public MovieAdapter(ArrayList<Movie> movies ) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public LayoutMovie onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_layout, viewGroup,false);
        LayoutMovie layoutMovie = new LayoutMovie(view);
        return layoutMovie;
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutMovie layoutMovie, int position) {

        Movie item = movies.get(position);

        layoutMovie.tvName.setText(item.getTitle());
        layoutMovie.tvOverview.setText(item.getOverview());
        layoutMovie.tvDate.setText(item.getReleaseDate());

        Glide.with(layoutMovie.itemView.getContext()).load(URL_IMG+item.getPosterPath()).into(layoutMovie.ivMovie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class LayoutMovie extends RecyclerView.ViewHolder {

        TextView tvName;

        TextView tvOverview;

        TextView tvDate;

        ImageView ivMovie;

        public LayoutMovie(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById (R.id.tvName);
            tvOverview = itemView.findViewById (R.id.tvOverview);
            tvDate = itemView.findViewById (R.id.tvDate);
            ivMovie = itemView.findViewById (R.id.ivMovie);
        }
    }
}
