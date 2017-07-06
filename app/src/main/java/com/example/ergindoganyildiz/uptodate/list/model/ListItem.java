package com.example.ergindoganyildiz.uptodate.list.model;

/**
 * Created by ergindoganyildiz on 7/5/17.
 */

public class ListItem {

    private String iconUrl;
    private String itemString;
    private String userIdStr;

    public ListItem(String iconUrl, String itemString, String userIdStr) {
        this.iconUrl = iconUrl;
        this.itemString = itemString;
        this.userIdStr = userIdStr;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getItemString() {
        return itemString;
    }

    public void setItemString(String itemString) {
        this.itemString = itemString;
    }

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }
}
