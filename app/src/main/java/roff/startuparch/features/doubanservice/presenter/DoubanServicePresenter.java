package roff.startuparch.features.doubanservice.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import roff.startuparch.features.doubanservice.beans.Subject;
import roff.startuparch.features.doubanservice.model.DoubanMovieService;
import roff.startuparch.features.doubanservice.view.IDoubanServiceView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wuyongbo on 16-6-12.
 */
public class DoubanServicePresenter implements IDoubanServicePresenter {

    final static String TAG = DoubanServicePresenter.class.getSimpleName();

    //FIXME
    private DoubanMovieService doubanMovieService;
    private IDoubanServiceView doubanServiceView;

    public DoubanServicePresenter(DoubanMovieService doubanMovieService) {
        this.doubanMovieService = doubanMovieService;
    }

    @Override
    public void setDoubanServiceView(@NonNull  IDoubanServiceView doubanServiceView) {
        this.doubanServiceView = doubanServiceView;
    }

    @Override
    public void getTop250Movies(int start, int end) {
        doubanMovieService.getTopMovies(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Subject>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onNext(List<Subject> subjects) {
                        Log.d(TAG, "onNext");
                        doubanServiceView.onMovieInfo(subjects);
                    }
                });
    }
}
