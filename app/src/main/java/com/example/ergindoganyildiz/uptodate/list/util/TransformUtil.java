package com.example.ergindoganyildiz.uptodate.list.util;

import com.example.ergindoganyildiz.uptodate.list.model.ListItem;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public class TransformUtil {

    public static List<ListItem> transformUserList(List<User> users){
        List<ListItem> result = new ArrayList<ListItem>();

        for(User user:users){
            result.add(new ListItem(user.profileImageUrl, user.screenName, user.idStr));
        }

        return result;
    }

    public static List<ListItem> transformTweetList(List<Tweet> tweets){
        List<ListItem> result = new ArrayList<ListItem>();

        for(Tweet tweet:tweets){
            result.add(new ListItem(tweet.user.profileImageUrl, tweet.text, tweet.user.idStr));
        }

        return result;
    }
}
