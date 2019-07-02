package com.code.alpha.alphamade.submission.fragment.detailmovie;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
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
import com.code.alpha.alphamade.submission.adapter.ProductionCompanyAdapter;
import com.code.alpha.alphamade.submission.connection.ApiService;
import com.code.alpha.alphamade.submission.model.Constant;
import com.code.alpha.alphamade.submission.model.NewMovieModel;
import com.code.alpha.alphamade.submission.model.ProductionCompany;
import com.code.alpha.alphamade.submission.utils.CompanyItemDecoration;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailMovieFragment extends Fragment implements DetailMovieContract.View {


    public DetailMovieFragment() {
    }

    private ImageView imgCover, photo;
    private TextView title, year, desc, length;
    private RatingBar ratingMovie;
    private DetailMoviePresenter presenter;
    private ProductionCompanyAdapter adapter;
    private ProgressBar progressBar;
    private RecyclerView rvCompany;
    private ArrayList<ProductionCompany> companies = new ArrayList<>();

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
        progressBar = view.findViewById(R.id.progress_bar);
        rvCompany = view.findViewById(R.id.rv_company);
        adapter = new ProductionCompanyAdapter(companies);
        presenter = ViewModelProviders.of(this).get(DetailMoviePresenter.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NewMovieModel movie = (NewMovieModel) (getArguments() != null ? getArguments().getParcelable(Constant.movie) : null);
        if (movie != null) {
            Picasso.get().load(BuildConfig.BASE_IMAGE_URL + movie.getPosterPath()).into(imgCover);
            Picasso.get().load(BuildConfig.BASE_IMAGE_URL + movie.getPosterPath()).resize(200, 300).into(photo);
            if (movie.getTitle() != null) {
                title.setText(movie.getTitle());
                year.setText(movie.getReleaseDate());
            } else {
                title.setText(movie.getOriginalName());
                year.setText(movie.getFirstAirDate());
            }
            desc.setText(movie.getOverview());
            String lenghtOfMinutes = String.format(getResources().getString(R.string.length_time), movie.getVoteCount().toString());
            length.setText(lenghtOfMinutes);
            ratingMovie.setRating((float) (movie.getVoteAverage() / 2));

            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            rvCompany.setLayoutManager(layoutManager);
            rvCompany.addItemDecoration(new CompanyItemDecoration(16));
            rvCompany.setAdapter(adapter);
            String url = String.format(getResources().getString(R.string.url_data),
                    movie.getId().toString(), BuildConfig.API_KEY);
            if (companies.size() == 0)
                presenter.getDetailMovies(getArguments().getString(Constant.type), url, this, ApiService.getServices(requireContext()));
            presenter.getLiveData().observe(this, getDetailMovies);
        }

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


    private Observer<ArrayList<ProductionCompany>> getDetailMovies = new Observer<ArrayList<ProductionCompany>>() {
        @Override
        public void onChanged(ArrayList<ProductionCompany> models) {
            if (models != null) {
                companies.addAll(models);
                adapter.notifyDataSetChanged();
                showLoading(false);
            }
        }
    };

}
