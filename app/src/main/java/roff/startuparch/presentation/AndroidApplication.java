package roff.startuparch.presentation;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import roff.startuparch.BuildConfig;
import roff.startuparch.presentation.di.AppComponent;
import roff.startuparch.presentation.di.AppComponentProvider;
import roff.startuparch.presentation.di.AppModule;
import roff.startuparch.presentation.di.DaggerAppComponent;

/**
 * Created by wuyongbo on 16-3-22.
 */
public class AndroidApplication extends Application implements AppComponentProvider {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    /**
     * DaggerAppComponent是dagger生成的中间类,命名方式是Dagger+component接口名
     */
    private void initializeInjector() {
        this.appComponent =
                DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    /**
     * 实现接口, 让所有拿到application context的对象能取到AppComponent
     */
    @Override
    public AppComponent getAppComponent() {
        return this.appComponent;
    }

    /**
     * 使用LeakCanary来检查内存泄露
     */
    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
