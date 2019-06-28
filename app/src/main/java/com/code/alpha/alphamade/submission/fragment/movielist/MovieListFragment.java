package com.code.alpha.alphamade.submission.fragment.movielist;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.code.alpha.alphamade.R;
import com.code.alpha.alphamade.submission.activity.DetailMovieActivity;
import com.code.alpha.alphamade.submission.activity.HomeActivity;
import com.code.alpha.alphamade.submission.adapter.MovieListAdapter;
import com.code.alpha.alphamade.submission.adapter.RecycleMovieListAdapter;
import com.code.alpha.alphamade.submission.fragment.detailmovie.DetailMovieFragment;
import com.code.alpha.alphamade.submission.model.Constant;
import com.code.alpha.alphamade.submission.model.Movie;
import com.code.alpha.alphamade.submission.model.Movies;
import com.code.alpha.alphamade.submission.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment implements MovieListContract.View{


    public static MovieListFragment newInstance(int type) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.type, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    private RecycleMovieListAdapter movieAdapter;
    private ArrayList<Movie> movies;
    private MovieListPresenter presenter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView lvMovie = view.findViewById(R.id.lv_movie);
        movies = new ArrayList<>();
        movieAdapter = new RecycleMovieListAdapter(requireContext(), movies, new RecycleMovieListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie item) {
                Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
                intent.putExtra(Constant.movie, item);
                startActivity(intent);
            }
        });
        lvMovie.addItemDecoration(new SpacesItemDecoration(16));
        lvMovie.setLayoutManager(new LinearLayoutManager(requireContext()));
        lvMovie.setAdapter(movieAdapter);
        presenter = new MovieListPresenter(requireContext(), this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getJsonMovies(getArguments() != null ? getArguments().getInt(Constant.type) : 0);
    }

    @Override
    public void showJsonMovies(Movies data) {
        movies.clear();
        movies.addAll(data.movies);
        movieAdapter.notifyDataSetChanged();
    }
}
