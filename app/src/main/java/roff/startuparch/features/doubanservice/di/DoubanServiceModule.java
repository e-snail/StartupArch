package roff.startuparch.features.doubanservice.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import roff.startuparch.core.di.module.ApiModule;
import roff.startuparch.features.doubanservice.model.DoubanMovieService;
import roff.startuparch.features.doubanservice.presenter.DoubanServicePresenter;
import roff.startuparch.features.doubanservice.presenter.IDoubanServicePresenter;

/**
 * Created by wuyongbo on 16-6-12.
 */
@Module
public class DoubanServiceModule {

    @Provides
    IDoubanServicePresenter providesDoubanServicePresenter(DoubanMovieService service) {
        return new DoubanServicePresenter(service);
    }

    @Provides
    DoubanMovieService providesDoubanMovieService(Retrofit retrofit, @Named(ApiModule.MockServer) boolean mockServer) {
        if (mockServer) {
            return null;
        } else {
            return retrofit.create(DoubanMovieService.class);
        }
    }
}
