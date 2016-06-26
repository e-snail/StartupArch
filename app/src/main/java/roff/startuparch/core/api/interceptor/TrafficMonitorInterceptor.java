package roff.startuparch.core.api.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wuyongbo on 16-6-25.
 */
public class TrafficMonitorInterceptor implements Interceptor {

    final String TAG = TrafficMonitorInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Request request = chain.request();

        Response response = chain.proceed(request);

        //TODO content length

        return response;
    }
}
