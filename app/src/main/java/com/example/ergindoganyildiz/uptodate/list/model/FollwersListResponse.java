package com.example.ergindoganyildiz.uptodate.list.model;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public class FollwersListResponse {

    @SerializedName("next_cursor")
    private long nextCursor;
    @SerializedName("next_cursor_str")
    private String nextCursorStr;
    @SerializedName("previous_cursor")
    private long previousCursor;
    @SerializedName("previous_cursor_str")
    private String previousCursorStr;

    private List<User> users;

    public long getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(long nextCursor) {
        this.nextCursor = nextCursor;
    }

    public String getNextCursorStr() {
        return nextCursorStr;
    }

    public void setNextCursorStr(String nextCursorStr) {
        this.nextCursorStr = nextCursorStr;
    }

    public long getPreviousCursor() {
        return previousCursor;
    }

    public void setPreviousCursor(long previousCursor) {
        this.previousCursor = previousCursor;
    }

    public String getPreviousCursorStr() {
        return previousCursorStr;
    }

    public void setPreviousCursorStr(String previousCursorStr) {
        this.previousCursorStr = previousCursorStr;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
