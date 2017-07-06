package com.example.ergindoganyildiz.uptodate.list.data;

import android.support.annotation.NonNull;

import com.example.ergindoganyildiz.uptodate.list.base.BaseInteractor;

/**
 * Created by ergindoganyildiz on 6/26/17.
 */

public interface TweetListInteractor extends BaseInteractor {

    void searchTweets(@NonNull String keyword, @NonNull BaseInteractor.TweetsLoadedCallback callback);
}
