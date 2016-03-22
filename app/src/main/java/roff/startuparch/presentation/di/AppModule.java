package roff.startuparch.presentation.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import roff.startuparch.presentation.AndroidApplication;

/**
 * Created by wuyongbo on 16-3-22.
 * Application级别的Module,提供跟Application同样生命周期的对象, 一般包括application context/Resource class等
 */
@Module
public class AppModule {

    private final AndroidApplication androidApplication;

    public AppModule(AndroidApplication application) {
        this.androidApplication = application;
    }

    /**
     * 通过@Provides来声明可以提供的对象
     * 跟
     */
    @Singleton
    @Provides
    Context getApplicationContext() {
        return androidApplication;
    }
}
