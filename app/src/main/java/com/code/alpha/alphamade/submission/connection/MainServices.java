package com.code.alpha.alphamade.submission.connection;

import com.code.alpha.alphamade.submission.model.DetailMovieResult;
import com.code.alpha.alphamade.submission.model.MoviesResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MainServices {
    @GET
    Observable<MoviesResult> getMovies(@Url String url);

    @GET
    Observable<DetailMovieResult> getDetailMovies(@Url String url);
}
