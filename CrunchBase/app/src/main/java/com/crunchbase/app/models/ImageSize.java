package com.crunchbase.app.models;

import com.google.gson.annotations.SerializedName;

public class ImageSize {

    protected static class Fields {
        public static final String WIDTH = "width";
        public static final String HEIGHT = "height";
        public static final String URL = "url";
    }

    @SerializedName(Fields.WIDTH)
    private Long mWidth;

    @SerializedName(Fields.HEIGHT)
    private Long mHeight;

    @SerializedName(Fields.URL)
    private String mUrl;

    public void setWidth(final Long width) {
        mWidth = width;
    }

    public void setHeight(final Long height) {
        mHeight = height;
    }

    public void setUrl(final String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

}
