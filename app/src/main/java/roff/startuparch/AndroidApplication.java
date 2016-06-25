package roff.startuparch;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import roff.startuparch.core.api.ApiURL;
import roff.startuparch.core.di.component.ApplicationComponent;
import roff.startuparch.core.di.component.ApplicationComponentProvider;
import roff.startuparch.core.di.component.DaggerApplicationComponent;
import roff.startuparch.core.di.module.AndroidModule;
import roff.startuparch.core.di.module.ApiModule;
import roff.startuparch.util.DeviceInfo;

/**
 * Created by wuyongbo on 16-3-22.
 */
public class AndroidApplication extends Application implements ApplicationComponentProvider {

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        DeviceInfo.getInstance().initialization(this);

        this.initializeInjector();
        this.initializeLeakDetection();

        Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build();
    }

    private void initializeInjector() {
        String osVersion = android.os.Build.VERSION.RELEASE;

        String versionName;
        String versionCode;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = String.valueOf(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            versionName = "1.0.0";
            versionCode = "1";
            e.printStackTrace();
        }

        AndroidModule androidModule = new AndroidModule(this);
        ApiModule apiModule = new ApiModule.Builder()
                .url(ApiURL.CURRENT.toString())
                .mockServer(false)
                .osVersion(osVersion)
                .versionName(versionName)
                .versionCode(versionCode)
                .cacheParentDir(getCacheDir())
                .builder();

        /**
         * DaggerApplicationComponent是dagger生成的中间类,命名方式是Dagger+component接口名
         */
        this.appComponent = DaggerApplicationComponent.builder()
                .androidModule(androidModule)
                .apiModule(apiModule)
                .build();
    }

    /**
     * 实现接口, 让所有拿到application context的对象能取到AppComponent
     */
    @Override
    public ApplicationComponent getApplicationComponent() {
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
