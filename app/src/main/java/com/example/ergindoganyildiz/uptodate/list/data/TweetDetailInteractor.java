package com.example.ergindoganyildiz.uptodate.list.data;

import com.example.ergindoganyildiz.uptodate.list.base.BaseInteractor;
import com.example.ergindoganyildiz.uptodate.list.model.FollwersListResponse;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ergindoganyildiz on 6/27/17.
 */

public interface TweetDetailInteractor extends BaseInteractor{

    interface TokenCallback{
        void tokenRetrievedSuccess();

        void tokenRetrievedFail();
    }

    interface TwitterService{

        String BASE_URL = "https://api.twitter.com";

        @FormUrlEncoded
        @POST("oauth2/token")
        Call<OAuth2Token> postCredentials(@Field("grant_type") String grantType);

        @GET("/1.1/statuses/user_timeline.json")
        Call<List<Tweet>> getUserDetails(@Header("Authorization") String auth, @Query("user_id") String userId, @Query("count") int count);

        @GET("/1.1/followers/list.json")
        Observable<FollwersListResponse> getFollowersList(@Query("user_id") String userId, @Query("count") int count, @Query("include_user_entities") boolean includeUserEntities);

    }

    void getToken(TokenCallback tokenCallback);

    void getUsersTweets(String user_id, int count, BaseInteractor.TweetsLoadedCallback tweetsLoadedCallback);

    Observable<FollwersListResponse> getFollowersList(String user_id, int count);
}
