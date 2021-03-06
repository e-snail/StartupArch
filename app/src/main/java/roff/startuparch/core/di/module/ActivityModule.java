package roff.startuparch.core.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import roff.startuparch.core.di.annotation.PerActivity;

/**
 * Created by wuyongbo on 16-3-22.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    Activity getActivity() {
        return activity;
    }
}
