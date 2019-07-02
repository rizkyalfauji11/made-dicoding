package com.code.alpha.alphamade.submission.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.code.alpha.alphamade.BuildConfig;
import com.code.alpha.alphamade.R;
import com.code.alpha.alphamade.submission.model.NewMovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleMovieListAdapter extends RecyclerView.Adapter<RecycleMovieListAdapter.ViewHolder> {

    private Context ctx;
    private ArrayList<NewMovieModel> list;
    private final OnItemClickListener listener;

    public RecycleMovieListAdapter(Context ctx, ArrayList<NewMovieModel> list, OnItemClickListener listener) {
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

        @SuppressLint("SetTextI18n")
        void bind(final NewMovieModel movie) {
            if (movie.getTitle() != null) {
                title.setText(movie.getTitle());
                year.setText(movie.getReleaseDate());
            } else {
                title.setText(movie.getOriginalName());
                year.setText(movie.getFirstAirDate());
            }


            body.setText((movie.getVoteCount()) + " " + ctx.getResources().getString(R.string.vote));

            rating.setRating((float) (movie.getVoteAverage() / 2));
            Picasso.get().load(BuildConfig.BASE_IMAGE_URL + movie.getPosterPath()).resize(200, 300).into(cover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NewMovieModel item);
    }
}
