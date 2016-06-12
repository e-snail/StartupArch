package roff.startuparch.features.doubanservice.view;

import android.app.Fragment;
import android.util.Log;

import java.util.List;

import roff.startuparch.features.doubanservice.beans.Subject;
import roff.startuparch.features.doubanservice.presenter.IDoubanServicePresenter;

/**
 * Created by wuyongbo on 16-6-12.
 */
public class DoubanServiceView extends Fragment implements IDoubanServiceView {

    final static String TAG = DoubanServiceView.class.getSimpleName();

    IDoubanServicePresenter presenter;

    public DoubanServiceView() {

    }

    @Override
    public void setPresenter(IDoubanServicePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onMovieInfo(List<Subject> subjects) {
        Log.d(TAG, "onMovieInfo size=" + subjects.size());
    }
}
