package roff.startuparch.features.githubservice.di;

import dagger.Component;
import roff.startuparch.core.di.annotation.PerActivity;
import roff.startuparch.core.di.component.ApplicationComponent;
import roff.startuparch.core.di.module.ActivityModule;
import roff.startuparch.features.githubservice.GithubServiceActivity;

/**
 * Created by wuyongbo on 16-6-8.
 */
@PerActivity
@Component (
        dependencies = ApplicationComponent.class,
        modules = {
                ActivityModule.class,
                GithubServiceModule.class
        }
)

public interface GithubServiceComponent {
    void inject(GithubServiceActivity githubServiceActivity);
}
