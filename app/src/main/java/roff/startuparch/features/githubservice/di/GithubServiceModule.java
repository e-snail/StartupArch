package roff.startuparch.features.githubservice.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import roff.startuparch.core.di.module.ApiModule;
import roff.startuparch.features.githubservice.model.GithubService;
import roff.startuparch.features.githubservice.presenter.GithubServicePresenter;
import roff.startuparch.features.githubservice.presenter.IGithubServicePresenter;

/**
 * Created by wuyongbo on 16-6-8.
 */
@Module
public class GithubServiceModule {

    @Provides
    IGithubServicePresenter provideGithubServicePresenter(GithubService githubService) {
        return new GithubServicePresenter(githubService);
    }

    @Provides
    GithubService providesGithubService(Retrofit retrofit, @Named(ApiModule.MockServer) boolean mockServer) {
        if (mockServer) {
            return null;
        } else {
            return retrofit.create(GithubService.class);
        }
    }
}
