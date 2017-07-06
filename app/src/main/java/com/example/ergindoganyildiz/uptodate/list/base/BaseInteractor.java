package com.example.ergindoganyildiz.uptodate.list.base;

import com.example.ergindoganyildiz.uptodate.list.model.FollwersListResponse;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by ergindoganyildiz on 6/29/17.
 */

public interface BaseInteractor {

    interface TweetsLoadedCallback{
        void onTweetsLoaded(List<Tweet> tweets);

        void onTweetsLoadedFailed(String message);
    }

    interface FollowersLoadedCallback{
        void onFollowersLoaded(FollwersListResponse response);

        void onFollowersLoadedFailed();
    }
}
