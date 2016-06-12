package roff.startuparch.features.doubanservice.presenter;

import roff.startuparch.features.doubanservice.view.IDoubanServiceView;

/**
 * Created by wuyongbo on 16-6-12.
 */
public interface IDoubanServicePresenter {
    void setDoubanServiceView(IDoubanServiceView view);
    void getTop250Movies(int start, int end);
}
