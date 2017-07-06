package com.example.ergindoganyildiz.uptodate.list.data;

import com.example.ergindoganyildiz.uptodate.list.base.BaseInteractor;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ergindoganyildiz on 6/26/17.
 */

public class TweetListInteractorImpl implements TweetListInteractor {

    /**
     * Use Twitter API to searc the keyword and notify presentor with a callback.
     *
     * @param keyword
     * @param callback
     */
    @Override
    public void searchTweets(String keyword, final BaseInteractor.TweetsLoadedCallback callback) {
        TwitterApiClient client = TwitterCore.getInstance().getApiClient();
        SearchService searchService = client.getSearchService();

        Call<Search> call = searchService.tweets(keyword, null, null, null, null, null, null, null, null, null);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                List<Tweet> tweets = response.body().tweets;
                callback.onTweetsLoaded(tweets);
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                callback.onTweetsLoadedFailed(t.getMessage());
            }
        });
    }

}
