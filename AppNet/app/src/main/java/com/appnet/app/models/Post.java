package com.appnet.app.models;

import com.appnet.app.providers.AppNetContentProvider.PostTable.Columns;
import com.google.gson.annotations.SerializedName;

import io.pivotal.arca.provider.ColumnName;

public class Post {

    protected static class Fields {
        public static final String ID = "id";
        public static final String CREATED_AT = "created_at";
        public static final String TEXT = "text";
        public static final String NUM_STARS = "num_stars";
        public static final String NUM_REPOSTS = "num_reposts";
        public static final String NUM_REPLIES = "num_replies";
        public static final String USER = "user";
    }

    @ColumnName(Columns.ID)
    @SerializedName(Fields.ID)
    private String mId;

    @ColumnName(Columns.CREATED_AT)
    @SerializedName(Fields.CREATED_AT)
    private String mCreatedAt;

    @ColumnName(Columns.TEXT)
    @SerializedName(Fields.TEXT)
    private String mText;

    @ColumnName(Columns.NUM_STARS)
    @SerializedName(Fields.NUM_STARS)
    private String mNumStars;

    @ColumnName(Columns.NUM_REPOSTS)
    @SerializedName(Fields.NUM_REPOSTS)
    private String mNumReposts;

    @ColumnName(Columns.NUM_REPLIES)
    @SerializedName(Fields.NUM_REPLIES)
    private String mNumReplies;

    @SerializedName(Fields.USER)
    private User mUser;

    @ColumnName(Columns.IMAGE_URL)
	public String getImageUrl() {
		return mUser != null ? mUser.getImageUrl() : null;
	}
	
}
