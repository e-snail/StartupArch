package roff.startuparch.features.auth.di;

import dagger.Component;
import roff.startuparch.core.di.module.ActivityModule;
import roff.startuparch.core.di.annotation.PerActivity;
import roff.startuparch.features.auth.AuthActivity;
import roff.startuparch.features.auth.presenter.AuthPresenter;

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
