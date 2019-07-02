package com.code.alpha.alphamade.submission.fragment.movielist;

import com.code.alpha.alphamade.submission.connection.MainServices;

interface MovieListContract {
    interface View {
        void showLoading(Boolean show);

        void showToast(String msg);
    }

    interface Presenter {
        void getMovies(String body, MovieListContract.View listener, MainServices service);
    }
}
