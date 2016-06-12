package roff.startuparch.features.doubanservice.model;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import roff.startuparch.features.doubanservice.beans.Subject;
import rx.Observable;

/**
 * Created by wuyongbo on 16-6-12.
 */
public interface DoubanMovieService {

    @GET("top250")
    Observable<List<Subject>> getTopMovies(@Query("start") int start, @Query("end") int end);
}
