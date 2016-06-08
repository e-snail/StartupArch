package roff.startuparch.core.api;

/**
 * Created by wuyongbo on 16-6-8.
 */
public enum ApiURL {

    RELEASE("https://api.github.com/"),     //Release server
    TEST("https://api.github.com/"),        //Testing server

    CURRENT("https://api.github.com/");

    private final String url;

    ApiURL(final String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
