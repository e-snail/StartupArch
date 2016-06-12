package roff.startuparch.features.doubanservice.di;

import dagger.Component;
import roff.startuparch.core.di.annotation.PerActivity;
import roff.startuparch.core.di.component.ApplicationComponent;
import roff.startuparch.core.di.module.ActivityModule;
import roff.startuparch.features.doubanservice.DoubanServiceActivity;

/**
 * Created by wuyongbo on 16-6-12.
 */
@PerActivity
@Component (
        dependencies = ApplicationComponent.class,
        modules = {
                ActivityModule.class,
                DoubanServiceModule.class
        }
)

public interface DoubanServiceComponent {
    void inject(DoubanServiceActivity activity);
}
