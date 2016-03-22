package roff.startuparch.presentation.features.auth.di;

import dagger.Component;
import roff.startuparch.presentation.di.ActivityModule;
import roff.startuparch.presentation.di.PerActivity;
import roff.startuparch.presentation.features.auth.AuthActivity;
import roff.startuparch.presentation.features.auth.presenter.AuthPresenter;

/**
 * Created by wuyongbo on 16-3-22.
 */

@PerActivity
@Component(
    dependencies = ActivityModule.class,
    modules = AuthModule.class
)
public interface AuthComponent {
    void inject(AuthActivity activity);

    AuthPresenter authPresenter();
}
