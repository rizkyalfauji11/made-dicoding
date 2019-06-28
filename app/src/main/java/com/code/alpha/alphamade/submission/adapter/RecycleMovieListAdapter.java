package com.code.alpha.alphamade.submission.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.code.alpha.alphamade.R;
import com.code.alpha.alphamade.submission.model.Constant;
import com.code.alpha.alphamade.submission.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleMovieListAdapter extends RecyclerView.Adapter<RecycleMovieListAdapter.ViewHolder> {

    private Context ctx;
    private ArrayList<Movie> list;
    private final OnItemClickListener listener;
    public RecycleMovieListAdapter(Context ctx, ArrayList<Movie> list, OnItemClickListener listener) {
        this.ctx = ctx;
        this.listener = listener;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new RecycleMovieListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, body, year;
        private RatingBar rating;
        private ImageView cover;
        ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
            body = view.findViewById(R.id.body);
            year = view.findViewById(R.id.year);
            rating = view.findViewById(R.id.rating);
            cover = view.findViewById(R.id.cover);
        }

        void bind(final Movie movie){
            title.setText(movie.getName());
            body.setText(movie.getDescription());
            year.setText(movie.getYear());
            rating.setRating(Float.parseFloat(String.valueOf(Float.valueOf(movie.getRating())/2)));
            int imgId = ctx.getResources().getIdentifier(movie.getImage(), Constant.drawable, ctx.getPackageName());
            Picasso.get().load(imgId).resize(150, 200).into(cover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Movie item);
    }
}
