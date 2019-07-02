package com.code.alpha.alphamade.submission.fragment.detailmovie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.code.alpha.alphamade.submission.connection.MainServices;
import com.code.alpha.alphamade.submission.model.DetailMovieResult;
import com.code.alpha.alphamade.submission.model.ProductionCompany;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailMoviePresenter extends ViewModel implements DetailMovieContract.Presenter {

    private MutableLiveData<ArrayList<ProductionCompany>> listMovies = new MutableLiveData<>();

    @Override
    public void getDetailMovies(String type, String url, final DetailMovieContract.View listener, MainServices service) {
        listener.showLoading(true);
        service.getDetailMovies(type + "/" + url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailMovieResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailMovieResult detailMovieResult) {
                        listMovies.postValue(detailMovieResult.getProductionCompanies());
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

    LiveData<ArrayList<ProductionCompany>> getLiveData() {
        return listMovies;
    }
}
