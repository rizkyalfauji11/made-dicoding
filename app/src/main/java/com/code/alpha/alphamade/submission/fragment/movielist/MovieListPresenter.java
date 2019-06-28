package com.code.alpha.alphamade.submission.fragment.movielist;

import android.content.Context;
import android.util.Log;

import com.code.alpha.alphamade.R;
import com.code.alpha.alphamade.submission.model.Movies;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MovieListPresenter implements MovieListContract.Presenter{
    private Context ctx;
    private MovieListContract.View view;

    public MovieListPresenter(Context ctx, MovieListContract.View view) {
        this.ctx = ctx;
        this.view = view;
    }


    @Override
    public void getJsonMovies(int fileName) {
        try{
            InputStream inputStream = ctx.getResources().openRawResource(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            Log.e("JSON", json);
            Movies movies = new Gson().fromJson(json, Movies.class);
            view.showJsonMovies(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
