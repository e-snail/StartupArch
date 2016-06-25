package roff.startuparch.core.api.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by wuyongbo on 16-6-25.
 */
public class TraficMonitorInterceptor implements Interceptor {
    final String TAG = TraficMonitorInterceptor.class.getSimpleName();

    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);
        ResponseBody body = response.body();
        long contentLength = body.contentLength();

        Log.d(TAG, "request content length=" + contentLength);

        return response;
    }
}
