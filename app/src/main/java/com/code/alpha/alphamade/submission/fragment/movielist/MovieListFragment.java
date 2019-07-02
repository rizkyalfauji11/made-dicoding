package com.code.alpha.alphamade.submission.fragment.movielist;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.code.alpha.alphamade.BuildConfig;
import com.code.alpha.alphamade.R;
import com.code.alpha.alphamade.submission.activity.DetailMovieActivity;
import com.code.alpha.alphamade.submission.adapter.RecycleMovieListAdapter;
import com.code.alpha.alphamade.submission.connection.ApiService;
import com.code.alpha.alphamade.submission.model.Constant;
import com.code.alpha.alphamade.submission.model.NewMovieModel;
import com.code.alpha.alphamade.submission.utils.SpacesItemDecoration;

import java.util.ArrayList;

public class MovieListFragment extends Fragment implements MovieListContract.View {


    public static MovieListFragment newInstance(String type) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.type, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    private RecycleMovieListAdapter movieAdapter;
    private ArrayList<NewMovieModel> movies = new ArrayList<>();
    private ProgressBar progressBar;
    private RecyclerView lvMovie;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvMovie = view.findViewById(R.id.lv_movie);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movieAdapter = new RecycleMovieListAdapter(requireContext(), movies, new RecycleMovieListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewMovieModel item) {
                Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
                intent.putExtra(Constant.movie, item);
                intent.putExtra(Constant.type, getArguments().getString(Constant.type));
                startActivity(intent);
            }
        });
        lvMovie.addItemDecoration(new SpacesItemDecoration(16));
        lvMovie.setLayoutManager(new LinearLayoutManager(requireContext()));
        lvMovie.setAdapter(movieAdapter);
        MovieListPresenter presenter = ViewModelProviders.of(this).get(MovieListPresenter.class);
        String url = String.format(getResources().getString(R.string.url_data),
                getArguments() != null ? getArguments().getString(Constant.type) : null, BuildConfig.API_KEY);
        if (movies.size() == 0)
            presenter.getMovies(url, this, ApiService.getServices(requireContext()));
        presenter.getLiveData().observe(this, getMovies);
    }

    @Override
    public void showLoading(Boolean show) {
        if (show) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private Observer<ArrayList<NewMovieModel>> getMovies = new Observer<ArrayList<NewMovieModel>>() {
        @Override
        public void onChanged(ArrayList<NewMovieModel> models) {
            if (models != null) {
                movies.addAll(models);
                movieAdapter.notifyDataSetChanged();
                showLoading(false);
            }
        }
    };

}
