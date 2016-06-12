package roff.startuparch.features.githubservice.presenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import roff.startuparch.features.githubservice.beans.GithubUserInfo;
import roff.startuparch.features.githubservice.beans.GithubUserRepo;
import roff.startuparch.features.githubservice.model.GithubService;
import roff.startuparch.features.githubservice.view.IGithubServiceView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wuyongbo on 16-6-8.
 */
public class GithubServicePresenter implements IGithubServicePresenter{

    private GithubService githubService;
    private IGithubServiceView githubServiceView;

    public GithubServicePresenter(GithubService githubService) {
        this.githubService = githubService;
    }

    @Override
    public void setIGithubServiceView(IGithubServiceView githubServiceView) {
        this.githubServiceView = githubServiceView;
    }

    @Override
    public void getUserRepos(String user) {
        /**
         * 使用RxJava异步请求
         * FIXME observeOn应该在主线程更新UI
         */
        githubService.userRepos(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GithubUserRepo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        githubServiceView.onUserRepoFail(e.getMessage());
                    }

                    @Override
                    public void onNext(List<GithubUserRepo> githubUserRepos) {
                        githubServiceView.onUserRepoSuccess(githubUserRepos);
                    }
                });
    }

    @Override
    public void getUserInfo(String user) {
        /**
         * 使用{@link retrofit2.Call}返回值
         */
        Call<GithubUserInfo> userInfo = githubService.userInfo(user);
        userInfo.enqueue(new Callback<GithubUserInfo>() {
            @Override
            public void onResponse(Call<GithubUserInfo> call, Response<GithubUserInfo> response) {
                githubServiceView.onUserInfoSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GithubUserInfo> call, Throwable t) {
                githubServiceView.onUserInfoFail(t.getMessage());
            }
        });
    }
}
