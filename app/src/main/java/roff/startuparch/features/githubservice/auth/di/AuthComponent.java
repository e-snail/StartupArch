package roff.startuparch.features.githubservice.auth.di;

import dagger.Component;
import roff.startuparch.core.di.ActivityModule;
import roff.startuparch.core.di.PerActivity;
import roff.startuparch.features.githubservice.auth.AuthActivity;
import roff.startuparch.features.githubservice.auth.presenter.AuthPresenter;

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
