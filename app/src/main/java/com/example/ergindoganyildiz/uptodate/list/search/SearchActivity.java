package com.example.ergindoganyildiz.uptodate.list.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ergindoganyildiz.uptodate.R;
import com.example.ergindoganyildiz.uptodate.list.data.TweetListInteractorImpl;
import com.example.ergindoganyildiz.uptodate.list.detail.DetailActivity;
import com.example.ergindoganyildiz.uptodate.list.model.ListItem;
import com.example.ergindoganyildiz.uptodate.list.util.TransformUtil;
import com.google.gson.Gson;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchContract.View, View.OnClickListener{

    @BindView(R.id.list_rv)
    public RecyclerView recyclerView;
    @BindView(R.id.list_pb)
    public ProgressBar progressBar;
    @BindView(R.id.list_no_tweets)
    public TextView noTweetsTextView;

    public ListAdapter listAdapter;
    public SearchContract.Presenter presenter;

    @BindView(R.id.list_activity_et) EditText editText;
    @BindView(R.id.list_activity_search) Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        Twitter.initialize(this);

        ButterKnife.bind(this);

        setPresenter(new SearchPresenter(this, new TweetListInteractorImpl()));


        recyclerView.setHasFixedSize(true);

        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        listAdapter = new ListAdapter(this, new ArrayList<ListItem>(), presenter);
        recyclerView.setAdapter(listAdapter);

        button.setOnClickListener(this);
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
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
    public void setItems(List<?> tweets) {
        listAdapter.setItems(TransformUtil.transformTweetList((List<Tweet>)tweets));
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
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void startDetailActivity(ListItem item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("TWEET", (new Gson().toJson(item)));
        startActivity(intent);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void disableSearchButton() {
        button.setEnabled(false);
    }

    @Override
    public void enableSearchButton() {
        button.setEnabled(true);
    }

    @Override
    public void onClick(View view) {
        presenter.onSearchButtonClicked(editText.getText().toString());
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
