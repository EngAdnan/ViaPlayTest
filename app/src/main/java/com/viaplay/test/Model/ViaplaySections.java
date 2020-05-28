package com.viaplay.test.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViaplaySections {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("href")
    @Expose
    private String href;


    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
