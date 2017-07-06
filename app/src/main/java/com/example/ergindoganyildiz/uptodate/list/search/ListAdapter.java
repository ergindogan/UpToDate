package com.example.ergindoganyildiz.uptodate.list.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ergindoganyildiz.uptodate.R;
import com.example.ergindoganyildiz.uptodate.list.base.BasePresenter;
import com.example.ergindoganyildiz.uptodate.list.model.ListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ergindoganyildiz on 6/26/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListItem> items;
    private BasePresenter presenter;
    private Picasso picasso;

    public ListAdapter(Context context, List<ListItem> items, BasePresenter presenter){
        this.items = items;
        this.presenter = presenter;
        picasso = Picasso.with(context);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_name) TextView text;
        @BindView(R.id.icon) ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.text.setText(items.get(position).getItemString());

        picasso.load(items.get(position).getIconUrl())
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error_icon)
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if(presenter instanceof SearchContract.Presenter){
                    ((SearchContract.Presenter) presenter).onItemClick(items.get(position));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ListItem> items){
        this.items = items;
    }

    public List<ListItem> getItems(){
        return items;
    }
}
