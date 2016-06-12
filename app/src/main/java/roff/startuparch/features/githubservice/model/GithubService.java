package roff.startuparch.features.githubservice.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import roff.startuparch.features.githubservice.beans.GithubUserRepo;
import roff.startuparch.features.githubservice.beans.GithubUserInfo;
import rx.Observable;

/**
 * Created by wuyongbo on 16-6-8.
 */
public interface GithubService {

    @GET("users/{user}/repos")
    Observable<List<GithubUserRepo>> userRepos(@Path("user") String user);

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("users/{user}")
    Call<GithubUserInfo> userInfo(@Path("user") String user);

}
