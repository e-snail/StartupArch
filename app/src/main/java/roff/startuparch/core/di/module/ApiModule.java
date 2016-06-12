package roff.startuparch.core.di.module;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import roff.startuparch.core.api.OKHttpBuilder;
import roff.startuparch.core.api.RetrofitBuilder;
import roff.startuparch.core.api.ApiURL;

/**
 * Created by wuyongbo on 16-6-8.
 *
 * 负责组合OKHttp和Retrofit，并提供Retrofit对象供其他模块调用
 */
@Module
public class ApiModule {

    private final String url;
    private final boolean mockServer;
    private final String versionName;
    private final String versionCode;
    private final String osVersion;
    private final File cacheParentDir;

    public static final String RetrofitURL = "RetrofitURL";
    public static final String MockServer = "MockServer";

    private ApiModule(String url, boolean mockServer, String osVersion, String versionName, String versionCode, File cacheParentDir) {
        this.url = url;
        this.mockServer = mockServer;
        this.osVersion = osVersion;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.cacheParentDir = cacheParentDir;
    }

    /**
     * 使用构造器模式创建ApiModule
     */
    public static class Builder {
        private String url;
        private boolean mockServer;
        private String versionName;
        private String versionCode;
        private String osVersion;
        private File cacheParentDir;

        public Builder() {}

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder mockServer(boolean mockServer) {
            this.mockServer = mockServer;
            return this;
        }

        public Builder versionName(String versionName) {
            this.versionName = versionName;
            return this;
        }

        public Builder versionCode(String versionCode) {
            this.versionCode = versionCode;
            return this;
        }

        public Builder osVersion(String osVersion) {
            this.osVersion = osVersion;
            return this;
        }

        public Builder cacheParentDir(File cacheParentDir) {
            this.cacheParentDir = cacheParentDir;
            return this;
        }

        public ApiModule builder() {
            return new ApiModule(url, mockServer, osVersion, versionName, versionCode, cacheParentDir);
        }
    }

    @Provides
    @Singleton
    OkHttpClient provideOKHttpClient() {
        return OKHttpBuilder.createHttpClient(cacheParentDir, mockServer, versionName, versionCode, osVersion);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {

        //Retrofit使用的URL
        String baseUrl = ApiURL.CURRENT.getUrl();
        //Retrofit使用OKHttp作为传输层
        OkHttpClient client = provideOKHttpClient();
        //数据解析器
        Converter.Factory convertFactory = JacksonConverterFactory.create();
        //请求回调的方式
        CallAdapter.Factory callAdapter = RxJavaCallAdapterFactory.create();

        return RetrofitBuilder.buildRetrofit(baseUrl, client, callAdapter, convertFactory);
    }

    @Provides
    @Named(RetrofitURL)
    public String retrofitURL() {
        return this.url;
    }

    @Provides
    @Named(MockServer)
    public boolean mockServer() {
        return this.mockServer;
    }
}
