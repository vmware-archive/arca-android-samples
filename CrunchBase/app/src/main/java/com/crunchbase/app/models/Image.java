package com.crunchbase.app.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Image {

    protected static class Fields {
        public static final String AVAILABLE_SIZES = "available_sizes";
        public static final String ATTRIBUTION = "attribution";
    }

    @SerializedName(Fields.AVAILABLE_SIZES)
    private List<ImageSize> mAvailableSizes;

    @SerializedName(Fields.ATTRIBUTION)
    private String mAttribution;

	public String getLastImageUrl() {
		if (mAvailableSizes != null && mAvailableSizes.size() > 0) {
			return mAvailableSizes.get(mAvailableSizes.size() - 1).getUrl();
		} else { 
			return null;
		}
	}
	
}
