package com.viaplay.test.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ViaplaySections extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("href")
    @Expose
    private String href;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        int startIndex = href.indexOf("{");
        return href.substring(0, startIndex);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHref(String href) {

        this.href = href;
    }
}
