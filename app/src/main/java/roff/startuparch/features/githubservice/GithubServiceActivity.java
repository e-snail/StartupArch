package roff.startuparch.features.githubservice;

import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.OnClick;
import roff.startuparch.R;
import roff.startuparch.core.component.BaseActivity;
import roff.startuparch.core.di.module.ActivityModule;
import roff.startuparch.features.githubservice.di.DaggerGithubServiceComponent;
import roff.startuparch.features.githubservice.di.GithubServiceComponent;
import roff.startuparch.features.githubservice.presenter.IGithubServicePresenter;
import roff.startuparch.features.githubservice.view.GithubServiceView;
import roff.startuparch.features.githubservice.view.IGithubServiceView;

/**
 * Created by wuyongbo on 16-6-8.
 */
public class GithubServiceActivity extends BaseActivity {

    private GithubServiceComponent githubServiceComponent;

    @Inject IGithubServicePresenter githubServicePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.githubservice);

        injectComponents();

        setupViewPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();

        //FIXME move following to View
        githubServicePresenter.getUserInfo("e-snail");
        githubServicePresenter.getUserRepos("e-snail");
    }

    private void setupViewPresenter() {
        IGithubServiceView githubServiceView = new GithubServiceView();
        githubServicePresenter.setIGithubServiceView(githubServiceView);
    }

    private void injectComponents() {
        getGithubServiceComponent().inject(this);
    }

    private GithubServiceComponent getGithubServiceComponent() {
        if (githubServiceComponent == null) {
            githubServiceComponent = DaggerGithubServiceComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }

        return githubServiceComponent;
    }

    @OnClick(R.id.user_name)
    void onClickUserName() {
        Toast.makeText(this, "Click name", Toast.LENGTH_LONG).show();
    }
}