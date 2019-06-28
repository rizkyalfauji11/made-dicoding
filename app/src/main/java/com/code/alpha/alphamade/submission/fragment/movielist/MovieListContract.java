package com.code.alpha.alphamade.submission.fragment.movielist;

import com.code.alpha.alphamade.submission.model.Movies;

interface MovieListContract {
    interface View{
        void showJsonMovies(Movies movies);
    }

    interface Presenter{
        void getJsonMovies(int fileName);
    }
}
