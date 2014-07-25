package com.appnet.app.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostsResponse {

    protected static class Fields {
        public static final String DATA = "data";
    }

    @SerializedName(Fields.DATA)
    private List<Post> mData;

    public List<Post> getData() {
        return mData;
    }
}
