package com.code.alpha.alphamade.submission.adapter;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.code.alpha.alphamade.R;
import com.code.alpha.alphamade.submission.model.Constant;
import com.code.alpha.alphamade.submission.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie> movies;

    public MovieListAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);

        }

        ViewHolder viewHolder = new ViewHolder(convertView);
        Movie movie = (Movie)getItem(position);
        viewHolder.bind(movie);

        return convertView;
    }

    private class ViewHolder {
        private TextView title, body, year;
        private RatingBar rating;
        private ImageView cover;

        ViewHolder (View view){
            title = view.findViewById(R.id.title);
            body = view.findViewById(R.id.body);
            year = view.findViewById(R.id.year);
            rating = view.findViewById(R.id.rating);
            cover = view.findViewById(R.id.cover);
        }

        void bind(Movie movie){
            title.setText(movie.getName());

            body.setText(movie.getDescription());
            year.setText(movie.getYear());
            rating.setRating(Float.parseFloat(String.valueOf(Float.valueOf(movie.getRating())/2)));
            int imgId = context.getResources().getIdentifier(movie.getImage(), Constant.drawable, context.getPackageName());
            Picasso.get().load(imgId).into(cover);
        }
    }
}
