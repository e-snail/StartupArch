package roff.startuparch.features.githubservice;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import javax.inject.Inject;

import roff.startuparch.R;
import roff.startuparch.core.component.BaseActivity;
import roff.startuparch.core.di.module.ActivityModule;
import roff.startuparch.features.githubservice.di.DaggerGithubServiceComponent;
import roff.startuparch.features.githubservice.di.GithubServiceComponent;
import roff.startuparch.features.githubservice.presenter.IGithubServicePresenter;
import roff.startuparch.features.githubservice.view.GithubServiceFragment;

/**
 * Created by wuyongbo on 16-6-8.
 */
public class GithubServiceActivity extends BaseActivity {

    private GithubServiceComponent githubServiceComponent;

    @Inject IGithubServicePresenter githubServicePresenter;

    GithubServiceFragment githubServiceFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.githubservice);

        githubServiceFragment = (GithubServiceFragment) getSupportFragmentManager().findFragmentById(R.id.githubservice_fragment);
        if (githubServiceFragment == null) {
            githubServiceFragment = new GithubServiceFragment();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.githubservice_fragment, githubServiceFragment);
        transaction.commit();

        injectComponents();

        setupViewPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();

        githubServicePresenter.getUserInfo("e-snail");
        githubServicePresenter.getUserRepos("e-snail");
    }

    private void setupViewPresenter() {
        githubServicePresenter.setIGithubServiceView(githubServiceFragment);
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
}