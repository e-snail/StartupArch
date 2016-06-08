package roff.startuparch.features.githubservice.auth.di;

import dagger.Module;
import dagger.Provides;
import roff.startuparch.core.di.PerActivity;
import roff.startuparch.features.githubservice.auth.AuthActivity;
import roff.startuparch.features.githubservice.auth.presenter.AuthPresenter;

/**
 * Created by wuyongbo on 16-3-22.
 */
@Module
public class AuthModule {

    private final AuthActivity authActivity;

    public AuthModule(AuthActivity activity) {
        this.authActivity = activity;
    }

    @PerActivity
    @Provides
    AuthPresenter authPresenter() {
        return new AuthPresenter();
    }
}
