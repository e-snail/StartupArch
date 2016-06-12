package roff.startuparch.features.doubanservice.view;

import java.util.List;

import roff.startuparch.features.doubanservice.beans.Subject;
import roff.startuparch.features.doubanservice.presenter.IDoubanServicePresenter;

/**
 * Created by wuyongbo on 16-6-12.
 */
public interface IDoubanServiceView {
    void setPresenter(IDoubanServicePresenter presenter);
    void onMovieInfo(List<Subject> subjects);
}
