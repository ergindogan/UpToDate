package com.example.ergindoganyildiz.uptodate;

import com.example.ergindoganyildiz.uptodate.list.data.TweetDetailInteractor;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ergindoganyildiz on 6/28/17.
 */

public class TweetDetailPresenterTest {

    @Mock
    private TweetDetailInteractor tweetDetailInteractor;

//    @Mock
//    private TweetDetailContract.FragmentView tweetDetailView;
//
//    private TweetDetailPresenter tweetDetailPresenter;

    @Mock
    private Tweet tweet;

    @Mock
    private User user;

    private List<Tweet> tweets;

    private List<Tweet> emptyTweetList = new ArrayList<Tweet>();

    @Before
    public void setupMocksAndView() {
        tweets = new ArrayList<Tweet>();

        MockitoAnnotations.initMocks(this);

        for(int i = 0; i < 20; i++){
            tweets.add(tweet);
        }
    }

    /*@Test
    public void createPresenter_setsThePresenterToView(){
        tweetDetailPresenter = new TweetDetailPresenter(tweetDetailView, tweetDetailInteractor);

        verify(tweetDetailView).setPresenter(tweetDetailPresenter);
    }

    @Test
    public void onResume_TweetDetailsRendered(){
        tweetDetailPresenter = new TweetDetailPresenter(tweetDetailView, tweetDetailInteractor);

        tweetDetailPresenter.onResume();

        verify(tweetDetailView).renderComponents();

        verify(tweetDetailInteractor).getToken(tweetDetailPresenter);
    }

    @Test
    public void onTweetsLoadedEmptyList_hideProgressAndTextSetsItems(){
        tweetDetailPresenter = new TweetDetailPresenter(tweetDetailView, tweetDetailInteractor);

        tweetDetailPresenter.onTweetsLoaded(emptyTweetList);

        verify(tweetDetailView).showNoItemsText();
        verify(tweetDetailView).setItems(emptyTweetList);
        verify(tweetDetailView).hideProgress();

    }

    @Test
    public void onTweetsLoaded_hideProgressAndTextSetsItems(){
        tweetDetailPresenter = new TweetDetailPresenter(tweetDetailView, tweetDetailInteractor);

        tweetDetailPresenter.onTweetsLoaded(tweets);

        verify(tweetDetailView).hideNoItemsText();
        verify(tweetDetailView).setItems(tweets);
        verify(tweetDetailView).hideProgress();
    }

    @Test
    public void onTweetsLoadedFailed_hideProgressShowNoItemsText(){
        tweetDetailPresenter = new TweetDetailPresenter(tweetDetailView, tweetDetailInteractor);

        tweetDetailPresenter.onTweetsLoadedFailed("null");

        verify(tweetDetailView).showNoItemsText();
        verify(tweetDetailView).hideProgress();
    }

    @Test
    public void getUsersTweets_showProgressHideTextcallApi(){
        tweetDetailPresenter = new TweetDetailPresenter(tweetDetailView, tweetDetailInteractor);

        tweetDetailPresenter.getUsersTweets(user.idStr, 20);

        verify(tweetDetailView).showProgress();
        verify(tweetDetailView).hideNoItemsText();

        verify(tweetDetailInteractor).getUsersTweets2(user.idStr, 20);

        verify(tweetDetailPresenter).onTweetsLoaded(tweets);

        verify(tweetDetailView).hideNoItemsText();
        verify(tweetDetailView).setItems(tweets);
        verify(tweetDetailView).hideProgress();
    }

    private void setReturningTweets() {
        when(tweetDetailInteractor.getUsersTweets2(user.idStr, 20)).thenReturn(Observable.just(tweets));
    }

    private void setReturningTweetsNotAvailable() {
        when(tweetDetailInteractor.getUsersTweets2(user.idStr, 20)).thenReturn(Observable.<List<Tweet>>error(new Exception()));
    }*/


}
