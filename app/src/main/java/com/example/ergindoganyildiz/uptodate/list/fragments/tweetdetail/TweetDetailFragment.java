package com.example.ergindoganyildiz.uptodate.list.fragments.tweetdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ergindoganyildiz.uptodate.R;
import com.example.ergindoganyildiz.uptodate.list.base.BasePresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public class TweetDetailFragment extends Fragment implements TweetDetailContract.View {

    @BindView(R.id.tweet_simple_user_icon) ImageView userIcon;
    @BindView(R.id.tweet_simple_tweet_text) TextView textView;

    private Picasso picasso;

    private String tweetText;
    private String userIamgeUrl;

    private TweetDetailPresenter presenter;

    public static TweetDetailFragment newInstance(String tweetText, String userIamgeUrl) {
        TweetDetailFragment fragment = new TweetDetailFragment();
        fragment.tweetText = tweetText;
        fragment.userIamgeUrl = userIamgeUrl;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tweet_detail_layout, container, false);

        ButterKnife.bind(this, rootView);
        presenter = new TweetDetailPresenter(this);

        picasso = Picasso.with(this.getContext());

        bindValuesIntoComponents();

        return rootView;
    }


    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (TweetDetailPresenter) presenter;
    }

    @Override
    public void bindValuesIntoComponents() {
        textView.setText(tweetText);
        picasso.load(userIamgeUrl)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error_icon)
                .into(userIcon);
    }
}
