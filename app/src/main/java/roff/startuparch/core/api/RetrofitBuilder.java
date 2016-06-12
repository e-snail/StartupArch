package roff.startuparch.core.api;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by wuyongbo on 16-6-8.
 */
public final class RetrofitBuilder {

    public static Retrofit buildRetrofit(@NonNull String baseUrl, @NonNull OkHttpClient client, @NonNull CallAdapter.Factory callAdapter,
                                         @NonNull Converter.Factory convertFactory)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(callAdapter)
                .addConverterFactory(convertFactory)
                .build();

        return retrofit;
    }
}