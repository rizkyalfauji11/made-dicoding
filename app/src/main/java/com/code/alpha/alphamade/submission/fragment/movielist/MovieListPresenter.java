package com.code.alpha.alphamade.submission.fragment.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.code.alpha.alphamade.submission.connection.MainServices;
import com.code.alpha.alphamade.submission.model.Constant;
import com.code.alpha.alphamade.submission.model.MoviesResult;
import com.code.alpha.alphamade.submission.model.NewMovieModel;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieListPresenter extends ViewModel implements MovieListContract.Presenter {

    private MutableLiveData<ArrayList<NewMovieModel>> listMovies = new MutableLiveData<>();

    @Override
    public void getMovies(String url, final MovieListContract.View listener, MainServices service) {
        listener.showLoading(true);
        service.getMovies(Constant.discover + "/" + url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoviesResult moviesResult) {
                        listMovies.postValue(moviesResult.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.showToast(e.getMessage());
                        listener.showLoading(false);
                    }

                    @Override
                    public void onComplete() {
                        listener.showLoading(false);
                    }
                });
    }

    LiveData<ArrayList<NewMovieModel>> getLiveData() {
        return listMovies;
    }
}
