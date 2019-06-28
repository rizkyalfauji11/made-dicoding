package com.code.alpha.alphamade.submission.fragment.detailmovie;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.code.alpha.alphamade.R;
import com.code.alpha.alphamade.exercise.activity.MainActivity;
import com.code.alpha.alphamade.submission.activity.DetailMovieActivity;
import com.code.alpha.alphamade.submission.activity.HomeActivity;
import com.code.alpha.alphamade.submission.model.Constant;
import com.code.alpha.alphamade.submission.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailMovieFragment extends Fragment {


    public DetailMovieFragment() {
    }

    private ImageView imgCover, photo;
    private TextView title, year, desc, length;
    private RatingBar ratingMovie;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgCover = view.findViewById(R.id.img_cover);
        photo = view.findViewById(R.id.photo);
        title = view.findViewById(R.id.title_movie);
        year = view.findViewById(R.id.year_movie);
        desc = view.findViewById(R.id.body);
        length = view.findViewById(R.id.length);
        ratingMovie = view.findViewById(R.id.rating);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Movie movie = (Movie) (getArguments() != null ? getArguments().getParcelable(Constant.movie) : null);
        if (movie != null) {
            int imgId = getResources().getIdentifier(movie.getImage(), Constant.drawable, requireContext().getPackageName());
            Picasso.get().load(imgId).into(imgCover);
            Picasso.get().load(imgId).into(photo);
            title.setText(movie.getName());
            desc.setText(movie.getDescription());
            String lenghtOfMinutes = String.format(getResources().getString(R.string.length_time), movie.getLenghtOfTime());
            length.setText(lenghtOfMinutes);
            year.setText(movie.getYear());
            ratingMovie.setRating(Float.parseFloat(String.valueOf(Float.valueOf(movie.getRating())/2)));
        }

    }
}
