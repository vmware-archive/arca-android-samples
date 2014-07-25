package com.appnet.app.models;

import com.google.gson.annotations.SerializedName;

public class User {

    protected static class Fields {
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String NAME = "name";
        public static final String TYPE = "type";
        public static final String AVATAR_IMAGE = "avatar_image";
    }

    @SerializedName(Fields.ID)
    private String mId;

    @SerializedName(Fields.USERNAME)
    private String mUsername;

    @SerializedName(Fields.NAME)
    private String mName;

    @SerializedName(Fields.TYPE)
    private String mType;

    @SerializedName(Fields.AVATAR_IMAGE)
    private AvatarImage mAvatarImage;

	public String getImageUrl() {
		return mAvatarImage != null ? mAvatarImage.getUrl() : null;
	}
	
}
