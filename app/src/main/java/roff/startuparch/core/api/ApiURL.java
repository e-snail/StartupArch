package roff.startuparch.core.api;

/**
 * Created by wuyongbo on 16-6-8.
 */
public enum ApiURL {

    RELEASE(URLConst.douban_server),     //Release server
    TEST(URLConst.douban_server),        //Testing server

    CURRENT(URLConst.douban_server);

    private final String url;

    ApiURL(final String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
