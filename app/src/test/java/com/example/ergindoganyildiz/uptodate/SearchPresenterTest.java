package com.example.ergindoganyildiz.uptodate;

import com.example.ergindoganyildiz.uptodate.list.base.BaseInteractor;
import com.example.ergindoganyildiz.uptodate.list.data.TweetListInteractor;
import com.example.ergindoganyildiz.uptodate.list.search.SearchContract;
import com.example.ergindoganyildiz.uptodate.list.search.SearchPresenter;
import com.example.ergindoganyildiz.uptodate.list.model.ListItem;
import com.twitter.sdk.android.core.models.Tweet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by ergindoganyildiz on 6/28/17.
 */

public class SearchPresenterTest {

    @Mock
    private TweetListInteractor tweetListInteractor;

    @Mock
    private SearchContract.View tweetListView;

    @Captor
    private ArgumentCaptor<BaseInteractor.TweetsLoadedCallback> searchTweetCallbackCaptor;

    private SearchPresenter searchPresenter;

    @Mock
    private ListItem item;

    @Before
    public void setupMocksAndView() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createPresenter_setsThePresenterToView(){
        // Get a reference to the class under test
        searchPresenter = new SearchPresenter(tweetListView, tweetListInteractor);

        // Then the presenter is set to the view
        verify(tweetListView).setPresenter(searchPresenter);
    }

    @Test
    public void onSearchButtonClicked_searchesKeyword(){
        String keyword = "Trump";

        // Get a reference to the class under test
        searchPresenter = new SearchPresenter(tweetListView, tweetListInteractor);

        searchPresenter.onSearchButtonClicked(keyword);

        // Verify that progress bar is shown.
        verify(tweetListView).showProgress();

        // Callback is captured and invoked.
        verify(tweetListInteractor).searchTweets(eq(keyword), searchTweetCallbackCaptor.capture());
        searchTweetCallbackCaptor.getValue().onTweetsLoaded(new ArrayList<Tweet>());

        // Then progress indicator is hidden.
        verify(tweetListView).hideProgress();
        ArgumentCaptor<List> showTasksArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(tweetListView).setItems(showTasksArgumentCaptor.capture());
        assertTrue(showTasksArgumentCaptor.getValue().size() == 0);
    }

    @Test
    public void onItemClick_opensDetailActivity(){
        searchPresenter = new SearchPresenter(tweetListView, tweetListInteractor);

        // Verify that onItemClick called.
        searchPresenter.onItemClick(item);

        verify(tweetListView).startDetailActivity(item);
    }
}
