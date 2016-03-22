package roff.startuparch.presentation.features.auth.di;

import dagger.Module;
import dagger.Provides;
import roff.startuparch.presentation.di.PerActivity;
import roff.startuparch.presentation.features.auth.AuthActivity;
import roff.startuparch.presentation.features.auth.presenter.AuthPresenter;

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
