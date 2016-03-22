package roff.startuparch.presentation.di;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

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
