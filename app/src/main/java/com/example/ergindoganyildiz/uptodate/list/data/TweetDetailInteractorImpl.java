package com.example.ergindoganyildiz.uptodate.list.data;

import android.util.Log;

import com.example.ergindoganyildiz.uptodate.list.base.BaseInteractor;
import com.example.ergindoganyildiz.uptodate.list.model.FollwersListResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;
import com.twitter.sdk.android.core.models.Tweet;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ergindoganyildiz on 6/27/17.
 */

public class TweetDetailInteractorImpl implements TweetDetailInteractor {

    private final String TAG = "TweetDetailInteractor";

    private TwitterService twitterService;

    private OAuth2Token token;
    private String consumerKey;
    private String consumerSecret;

    /**
     * Set up OkHttpClient and add an interceptor to add token.
     */

    public TweetDetailInteractorImpl(){
        TwitterAuthConfig authConfig = TwitterCore.getInstance().getAuthConfig();

        consumerKey = authConfig.getConsumerKey();
        consumerSecret = authConfig.getConsumerSecret();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        token != null ? token.getTokenType() + " " + token.getAccessToken() : Credentials.basic(consumerKey, consumerSecret));

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(TwitterService.BASE_URL)
                .build();

        twitterService = retrofit.create(TwitterService.class);
    }

    /**
     *
     * Use Retrofit to get token and callback to notify presenter.
     *
     * @param tokenCallback
     */

    @Override
    public void getToken(final TokenCallback tokenCallback){
        Call<OAuth2Token> call = twitterService.postCredentials("client_credentials");

        call.enqueue(new Callback<OAuth2Token>() {
            @Override
            public void onResponse(Call<OAuth2Token> call, Response<OAuth2Token> response) {
                // handle success
                if(response.isSuccessful()){
                    Log.d(TAG, "Token retreived successfully!");
                    token = response.body();
                    Log.d(TAG, "Token : " + token.getAccessToken());
                    tokenCallback.tokenRetrievedSuccess();
                }else{
                    Log.d(TAG, "Code: " + response.code() + "Message: " + response.message());
                    tokenCallback.tokenRetrievedFail();
                }
            }

            @Override
            public void onFailure(Call<OAuth2Token> call, Throwable t) {
                t.printStackTrace();
                tokenCallback.tokenRetrievedFail();
            }
        });
    }

    /**
     *
     * Use Retrofit to get user's tweets and callback to notify presenter.
     *
     * @param user_id
     * @param count
     * @param tweetsLoadedCallback
     */

    @Override
    public void getUsersTweets(String user_id, int count, final BaseInteractor.TweetsLoadedCallback tweetsLoadedCallback) {
        Call<List<Tweet>> call = twitterService.getUserDetails("Bearer " + token.getAccessToken(), user_id, count);

        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                // handle success
                if(response.isSuccessful()){
                    Log.d(TAG, "Tweets retreived successfully!");
                    List<Tweet> tweets = response.body();
                    for(int i = 0 ; i < tweets.size(); i++){
                        Log.d(TAG, "Tweet no : " + i + " :  " + tweets.get(i).text);
                    }
                    tweetsLoadedCallback.onTweetsLoaded(tweets);
                }else{
                    String message = "Code: " + response.code() + "Message: " + response.message();
                    Log.d(TAG, message);
                    tweetsLoadedCallback.onTweetsLoadedFailed(message);
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                tweetsLoadedCallback.onTweetsLoadedFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    /**
     *
     * Use RxJava2 to get user's follewers.
     *
     * @param user_id
     * @param count
     * @return
     */
    @Override
    public Observable<FollwersListResponse> getFollowersList(String user_id, int count) {
        return twitterService.getFollowersList(user_id, count, true);
    }
}
