package roff.startuparch.features.doubanservice;

import android.os.Bundle;

import javax.inject.Inject;

import roff.startuparch.core.component.BaseActivity;
import roff.startuparch.core.di.module.ActivityModule;
import roff.startuparch.features.doubanservice.di.DoubanServiceComponent;
import roff.startuparch.features.doubanservice.presenter.IDoubanServicePresenter;
import roff.startuparch.features.doubanservice.view.DoubanServiceView;
import roff.startuparch.features.doubanservice.di.DaggerDoubanServiceComponent;

/**
 * Created by wuyongbo on 16-6-12.
 */
public class DoubanServiceActivity extends BaseActivity {

    @Inject
    IDoubanServicePresenter doubanServicePresenter;

    DoubanServiceComponent doubanServiceComponent;

    //FIXME
    DoubanServiceView doubanServiceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectComponents();

        setupViewPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();

        doubanServicePresenter.getTop250Movies(0, 10);
    }

    private void setupViewPresenter() {
        doubanServicePresenter.setDoubanServiceView(doubanServiceView);
    }

    private void injectComponents() {
        getDoubanServiceComponent().inject(this);
    }

    private DoubanServiceComponent getDoubanServiceComponent() {
        if (doubanServiceComponent == null) {
            doubanServiceComponent = DaggerDoubanServiceComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }

        return doubanServiceComponent;
    }
}
