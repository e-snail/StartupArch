package roff.startuparch.core.di.component;

import android.app.Application;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.content.res.Resources;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import roff.startuparch.AndroidApplication;
import roff.startuparch.core.component.BaseActivity;
import roff.startuparch.core.di.module.AndroidModule;
import roff.startuparch.core.di.module.ApiModule;

/**
 * Created by wuyongbo on 16-6-8.
 */

/**
 * Component包含生命周期内（这里是Application）的所有Modules
 */
@Singleton
@Component(
        modules = {
                AndroidModule.class,
                ApiModule.class
        }
)

public interface ApplicationComponent {

    /**
     * Injections
     */
    void inject(AndroidApplication application);
    void inject(BaseActivity baseFragmentActivity);

    /**
     * Application级的公用功能模块或信息
     */
    Application application();
    Resources resources();
    NotificationManager notificationManager();
    SharedPreferences sharedPreferences();
    OkHttpClient httpClient();
    Retrofit retrofit();

    @Named(ApiModule.RetrofitURL) String retrofitURL();
    @Named(ApiModule.MockServer) boolean mockServer();

    @Named(AndroidModule.OS_VERSION) String osVersion();
    @Named(AndroidModule.IMEI) String imei();
    @Named(AndroidModule.VERSION_NAME) String versionName();
    @Named(AndroidModule.VERSION_CODE) String versionCode();
    @Named(AndroidModule.USERAGENT) String userAgent();
}
