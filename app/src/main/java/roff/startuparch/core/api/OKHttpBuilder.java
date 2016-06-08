package roff.startuparch.core.api;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import roff.startuparch.BuildConfig;
import roff.startuparch.util.DeviceInfo;

/**
 * Created by wuyongbo on 16-6-8.
 * 负责创建OKHttp对象实例，初始化对象参数，例如超时时间/Cookie信息/Http请求的信息/缓存路径和大小等等
 */
public final class OKHttpBuilder {

    final static String TAG = OKHttpBuilder.class.getSimpleName();

    final static long MAX_CACHE_SIZE   = 16 * 1024 * 1024;  //http请求的缓存大小，16M
    final static int TIMEOUT_CONNECT   = 15 * 1000;         //请求超时时间
    //FIXME
    final static int TIMEOUT_READ      = 20 * 1000;         //读文件超时???
    final static int TIMEOUT_WRITE     = 20 * 1000;         //写文件超时???

    private OKHttpBuilder() {}

    /**
     * create default OKHttpClient
     * @return
     */
    private static OkHttpClient createDefaultOkHttpClient() {

        final OkHttpClient client = new OkHttpClient();

        client.newBuilder()
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.MILLISECONDS);

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
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, "------->" + message);
            }
        });

        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                    .setLevel(HttpLoggingInterceptor.Level.HEADERS);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        /**
         * 插入默认拦截器/缓存等
         */
        OkHttpClient okHttpClient = createDefaultOkHttpClient();
        okHttpClient.newBuilder()
                .addInterceptor(new HttpSettingsInterceptor(osVersion, versionName, versionCode))
                .addInterceptor(new TimeConsumingInterceptor())
                .addInterceptor(logging)
                .cache(cache);

        return okHttpClient;
    }

    /**
     * 网络请求默认设置拦截器
     */
    static class HttpSettingsInterceptor implements Interceptor {

        String osVersion;
        String versionName;
        String versionCode;

        public HttpSettingsInterceptor(final String osVersion, final String versionName, final String versionCode) {
            this.osVersion = osVersion;
            this.versionCode = versionCode;
            this.versionName = versionName;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            try {
                HashMap<String, String> cookieMap = new HashMap<>();
                cookieMap.put("IMEI", DeviceInfo.getInstance().getIMEI());

                String cookieInfo = cookieMap.toString();

                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .addHeader("OSVersion", osVersion)
                        .addHeader("VName", versionName)
                        .addHeader("VCode", versionCode)
                        .addHeader("Cookie", cookieInfo)
                        .build();

                return chain.proceed(request);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 记录网络请求耗时的拦截器
     */
    static class TimeConsumingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            return null;
        }
    }
}