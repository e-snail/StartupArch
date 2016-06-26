package roff.startuparch.core.api.interceptor;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import roff.startuparch.util.DeviceInfo;

/**
 * Created by wuyongbo on 16-6-25.
 * 网络请求默认设置拦截器
 */
public class HttpSettingsInterceptor implements Interceptor {

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
