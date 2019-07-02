package com.code.alpha.alphamade.submission.fragment.detailmovie;

import com.code.alpha.alphamade.submission.connection.MainServices;

interface DetailMovieContract {
    interface View{
        void showLoading(Boolean show);
        void showToast(String msg);
    }

    interface Presenter{
        void getDetailMovies(String type, String body, DetailMovieContract.View listener, MainServices service);
    }
}
