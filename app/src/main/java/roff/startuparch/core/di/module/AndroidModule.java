package roff.startuparch.core.di.module;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import roff.startuparch.AndroidApplication;
import roff.startuparch.util.DeviceInfo;
import roff.startuparch.util.PreferenceConstant;

/**
 * Created by wuyongbo on 16-6-8.
 *
 * 为其它模块提供最常用的系统信息或设备信息
 */
@Module
public class AndroidModule {

    public static final String IMEI         = "IMEI";
    public static final String DEVICE_ID    = "DeviceID";
    public static final String OS_VERSION   = "OSVersion";
    public static final String VERSION_NAME = "VERSION_NAME";
    public static final String VERSION_CODE = "VERSION_CODE";
    public static final String USERAGENT    = "USERAGENT";

    private static final String DEFAULT_USER_AGENT = "User-Agent: Android Chrome";

    private final AndroidApplication application;

    public AndroidModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }

    @Provides
    @Singleton
    NotificationManager provideNotificationManager() {
        return (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return application.getSharedPreferences(PreferenceConstant.SHARED_PREFERENCE_MAIN, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @Named(OS_VERSION)
    String provideOSVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    @Provides
    @Singleton
    @Named(VERSION_CODE)
    String provideVersionCode() {
        try {
            PackageInfo packageInfo = application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
            return String.valueOf(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    @Singleton
    @Named(VERSION_NAME)
    String provideVersionName() {
        try {
            PackageInfo packageInfo = application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    @Singleton
    @Named(IMEI)
    String provideIMEI() {
        return DeviceInfo.getInstance().getIMEI();
    }

    @Provides
    @Singleton
    @Named(USERAGENT)
    String provideUserAgent() {
        return DEFAULT_USER_AGENT;
    }
}