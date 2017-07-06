package com.example.ergindoganyildiz.uptodate.list.base.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ergindoganyildiz.uptodate.R;
import com.example.ergindoganyildiz.uptodate.list.base.BasePresenter;
import com.example.ergindoganyildiz.uptodate.list.data.TweetDetailInteractorImpl;
import com.example.ergindoganyildiz.uptodate.list.fragments.usersfollowers.UsersFollowersContract;
import com.example.ergindoganyildiz.uptodate.list.fragments.usersfollowers.UsersFollowersPresenter;
import com.example.ergindoganyildiz.uptodate.list.fragments.userstweets.UsersTweetsContract;
import com.example.ergindoganyildiz.uptodate.list.fragments.userstweets.UsersTweetsPresenter;
import com.example.ergindoganyildiz.uptodate.list.search.ListAdapter;
import com.example.ergindoganyildiz.uptodate.list.model.ListItem;
import com.example.ergindoganyildiz.uptodate.list.util.TransformUtil;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ergindoganyildiz on 7/6/17.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseFragmentView<T> {

    @BindView(R.id.list_rv) public RecyclerView recyclerView;
    @BindView(R.id.list_pb) public ProgressBar progressBar;
    @BindView(R.id.list_no_tweets) public TextView noTweetsTextView;

    private ListAdapter listAdapter;
    private T presenter;

    protected String userId;
    protected int count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_layout, container, false);
        ButterKnife.bind(this, rootView);

        if(this instanceof UsersTweetsContract.FragmentView){
            setPresenter((T) new UsersTweetsPresenter((UsersTweetsContract.FragmentView)this, new TweetDetailInteractorImpl()));
        }else{
            setPresenter((T) new UsersFollowersPresenter((UsersFollowersContract.FragmentView)this, new TweetDetailInteractorImpl()));
        }
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(myLayoutManager);
        listAdapter = new ListAdapter(getActivity(), new ArrayList<ListItem>(), presenter);
        recyclerView.setAdapter(listAdapter);

        return rootView;
    }

    @Override
    public void setPresenter(T presenter) {
        this.presenter = (T) presenter;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<?> items) {
        List<ListItem> itemsToPass = new ArrayList<>();
        if(!items.isEmpty()){
            if(items.get(0) instanceof Tweet){
                itemsToPass = TransformUtil.transformTweetList((List<Tweet>) items);
            }else{
                itemsToPass = TransformUtil.transformUserList((List<User>) items);
            }
        }
        listAdapter.setItems(itemsToPass);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoItemsText() {
        noTweetsTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoItemsText() {
        noTweetsTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onTokenLoaded() {
        if(presenter instanceof UsersTweetsPresenter){
            ((UsersTweetsPresenter) presenter).getUsersTweets(userId, count);
        }else{
            ((UsersFollowersPresenter) presenter).getUsersFollowers(userId, count);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
