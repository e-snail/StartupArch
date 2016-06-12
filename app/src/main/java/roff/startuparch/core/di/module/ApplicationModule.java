package roff.startuparch.core.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import roff.startuparch.AndroidApplication;

/**
 * Created by wuyongbo on 16-6-8.
 */
@Module
public class ApplicationModule {

    private final AndroidApplication androidApplication;

    public ApplicationModule(AndroidApplication application) {
        this.androidApplication = application;
    }

    @Singleton
    @Provides
    Context getApplicationContext() {
        return androidApplication;
    }
}
