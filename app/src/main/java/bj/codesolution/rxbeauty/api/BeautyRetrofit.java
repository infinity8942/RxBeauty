package bj.codesolution.rxbeauty.api;

import bj.codesolution.rxbeauty.model.BeautyData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public interface BeautyRetrofit {

    @GET("meinv/")
    Observable<BeautyData> getBeauty(@Query("key") String key, @Query("page") int page, @Query("num") int num);

}
