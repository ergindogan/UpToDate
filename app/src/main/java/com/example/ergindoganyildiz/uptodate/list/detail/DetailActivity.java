package com.example.ergindoganyildiz.uptodate.list.detail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.ergindoganyildiz.uptodate.R;
import com.example.ergindoganyildiz.uptodate.list.base.BasePresenter;
import com.example.ergindoganyildiz.uptodate.list.data.TweetDetailInteractorImpl;
import com.example.ergindoganyildiz.uptodate.list.fragments.tweetdetail.TweetDetailFragment;
import com.example.ergindoganyildiz.uptodate.list.fragments.usersfollowers.UsersFollowersFragment;
import com.example.ergindoganyildiz.uptodate.list.fragments.userstweets.UsersTweetsFragment;
import com.example.ergindoganyildiz.uptodate.list.model.ListItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.container) ViewPager mViewPager;

    @BindView(R.id.tabs) TabLayout tabLayout;

    private List<Fragment> fragments;

    private DetailContract.Presenter presenter;

    private ListItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_holder_layout);
        ButterKnife.bind(this);

        item = (new Gson()).fromJson(getIntent().getStringExtra("TWEET"), ListItem.class);

        presenter = new DetailPresenter(this, new TweetDetailInteractorImpl());

        fragments = new ArrayList<>();
        fragments.add(TweetDetailFragment.newInstance(item.getItemString(), item.getIconUrl()));
        fragments.add(UsersTweetsFragment.newInstance(item.getUserIdStr(), 20));
        fragments.add(UsersFollowersFragment.newInstance(item.getUserIdStr(), 30));

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fragments);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (DetailContract.Presenter) presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
