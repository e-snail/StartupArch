package roff.startuparch.core.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import roff.startuparch.BuildConfig;
import roff.startuparch.core.api.interceptor.HttpSettingsInterceptor;
import roff.startuparch.core.api.interceptor.TimeConsumingInterceptor;
import roff.startuparch.core.api.interceptor.TrafficMonitorInterceptor;

/**
 * Created by wuyongbo on 16-6-8.
 * 负责创建OKHttp对象实例，初始化对象参数，例如超时时间/Cookie信息/Http请求的信息/缓存路径和大小等等
 */
public final class OKHttpBuilder {

    final static String TAG = OKHttpBuilder.class.getSimpleName();

    final static long MAX_CACHE_SIZE   = 16 * 1024 * 1024;  //http请求的缓存大小，16M
    final static int TIMEOUT_CONNECT   = 15 ;         //请求超时时间
    final static int TIMEOUT_READ      = 15;         //读文件超时
    final static int TIMEOUT_WRITE     = 15;         //写文件超时

    private OKHttpBuilder() {}

    /**
     * create default OKHttpClient
     * @return
     */
    private static OkHttpClient createDefaultOkHttpClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT,    TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ,          TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE,        TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        return client;
    }

    /**
     * create default OKHttpClient with cache
     * @return
     */
    public static OkHttpClient createHttpClient(final File cacheParentDir,
                                                boolean useMock, final String versionName, final String versionCode,
                                                final String osVersion)
    {
        if (cacheParentDir == null) {
            throw new IllegalArgumentException("cacheParentDir cannot be null");
        }

        final File cacheDir = new File(cacheParentDir, "okhttp_cache");
        final Cache cache = new Cache(cacheDir, MAX_CACHE_SIZE);

        /**
         * Log拦截器，OKHttp自带的
         */
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            loggingInterceptor
                    .setLevel(HttpLoggingInterceptor.Level.BASIC)
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                    .setLevel(HttpLoggingInterceptor.Level.HEADERS);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        /**
         * stetho拦截器
         */
        StethoInterceptor stethoInterceptor = new StethoInterceptor();

        /**
         * 插入默认拦截器/缓存等
         */
        OkHttpClient okHttpClient = createDefaultOkHttpClient();

        okHttpClient = okHttpClient.newBuilder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new HttpSettingsInterceptor(osVersion, versionName, versionCode))
                .addInterceptor(new TimeConsumingInterceptor())
                .addInterceptor(new TrafficMonitorInterceptor())
                .addInterceptor(stethoInterceptor)
                .cache(cache)
                .build();

        return okHttpClient;
    }
}
